package disruptor.netty.server;

import com.lmax.disruptor.YieldingWaitStrategy;
import com.lmax.disruptor.dsl.ProducerType;
import disruptor.netty.common.disruptor.MessageConsumer;
import disruptor.netty.common.disruptor.RingBufferWorkerPoolFactory;

public class NettyServerApp {
  public static void main(String[] args) {
    MessageConsumer[] conusmers = new MessageConsumer[8];
    for (int i = 0; i < conusmers.length; i++) {
      MessageConsumer messageConsumer = new MessageConsumerImpl4Server("code:serverId:" + i);
      conusmers[i] = messageConsumer;
    }
    RingBufferWorkerPoolFactory.getInstance()
        .initAndStart(
            ProducerType.MULTI,
            1024 * 1024,
            new YieldingWaitStrategy(),
            // new BlockingWaitStrategy(),
            conusmers);
    new NettyServer();
  }
}
