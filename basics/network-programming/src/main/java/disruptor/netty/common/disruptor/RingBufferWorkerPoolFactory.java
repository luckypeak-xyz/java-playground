package disruptor.netty.common.disruptor;

import com.lmax.disruptor.*;
import com.lmax.disruptor.dsl.ProducerType;
import disruptor.netty.common.entity.TranslatorDataWrapper;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;

public class RingBufferWorkerPoolFactory {
  // 需要生产者池和消费者池管理生产和消费者。
  private static Map<String, MessageProducer> producers =
      new ConcurrentHashMap<String, MessageProducer>();
  private static Map<String, MessageConsumer> consumers =
      new ConcurrentHashMap<String, MessageConsumer>();
  private RingBuffer<TranslatorDataWrapper> ringBuffer;
  private SequenceBarrier sequenceBarrier;
  private WorkerPool<TranslatorDataWrapper> workerPool;

  private RingBufferWorkerPoolFactory() {}

  public static RingBufferWorkerPoolFactory getInstance() {
    return SingletonHolder.instance;
  }

  // 初始化ProducerType生产者类型，是多生产还是单生产。MessageConsumer[]多消费者
  public void initAndStart(
      ProducerType type,
      int bufferSize,
      WaitStrategy waitStrategy,
      MessageConsumer[] messageConsumers) {
    // 1. 构建ringBuffer对象
    this.ringBuffer =
        RingBuffer.create(
            type,
            new EventFactory<TranslatorDataWrapper>() {
              public TranslatorDataWrapper newInstance() {
                return new TranslatorDataWrapper();
              }
            },
            bufferSize,
            waitStrategy);
    // 2.设置序号栅栏
    this.sequenceBarrier = this.ringBuffer.newBarrier();
    // 3.设置工作池
    this.workerPool =
        new WorkerPool<TranslatorDataWrapper>(
            this.ringBuffer, this.sequenceBarrier, new EventExceptionHandler(), messageConsumers);
    // 4 把所构建的消费者置入池中
    for (MessageConsumer mc : messageConsumers) {
      this.consumers.put(mc.getConsumerId(), mc);
    }
    // 5 添加我们的sequences
    this.ringBuffer.addGatingSequences(this.workerPool.getWorkerSequences());
    // 6 启动我们的工作池
    this.workerPool.start(
        Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() / 2));
  }

  public MessageProducer getMessageProducer(String producerId) {
    // 池里有直接获取生产者
    MessageProducer messageProducer = this.producers.get(producerId);
    if (null == messageProducer) {
      messageProducer = new MessageProducer(producerId, this.ringBuffer);
      this.producers.put(producerId, messageProducer);
    }
    return messageProducer;
  }

  // 单例
  private static class SingletonHolder {
    static final RingBufferWorkerPoolFactory instance = new RingBufferWorkerPoolFactory();
  }

  /** 异常静态类 */
  static class EventExceptionHandler implements ExceptionHandler<TranslatorDataWrapper> {
    public void handleEventException(Throwable ex, long sequence, TranslatorDataWrapper event) {}

    public void handleOnStartException(Throwable ex) {}

    public void handleOnShutdownException(Throwable ex) {}
  }
}
