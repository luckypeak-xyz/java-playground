package disruptor.netty.common.disruptor;

import com.lmax.disruptor.RingBuffer;
import disruptor.netty.common.entity.TranslatorData;
import disruptor.netty.common.entity.TranslatorDataWrapper;
import io.netty.channel.ChannelHandlerContext;

public class MessageProducer {
  private String producerId;

  private RingBuffer<TranslatorDataWrapper> ringBuffer;

  public MessageProducer(String producerId, RingBuffer<TranslatorDataWrapper> ringBuffer) {
    this.producerId = producerId;
    this.ringBuffer = ringBuffer;
  }

  public void onData(TranslatorData data, ChannelHandlerContext ctx) {
    long sequence = ringBuffer.next();
    try {
      TranslatorDataWrapper wrapper = ringBuffer.get(sequence);
      wrapper.setData(data);
      wrapper.setCtx(ctx);
    } finally {
      ringBuffer.publish(sequence);
    }
  }
}