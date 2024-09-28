package disruptor.netty.client;

import disruptor.netty.common.disruptor.MessageProducer;
import disruptor.netty.common.disruptor.RingBufferWorkerPoolFactory;
import disruptor.netty.common.entity.TranslatorData;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ClientHandler extends ChannelInboundHandlerAdapter {
  @Override
  public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
    //    try {
    //      TranslatorData response = (TranslatorData) msg;
    //      log.info(
    //          "Client端: id= "
    //              + response.getId()
    //              + ", name= "
    //              + response.getName()
    //              + ", message= "
    //              + response.getMessage());
    //    } finally {
    //      // 一定要注意 用完了缓存 要进行释放
    //      ReferenceCountUtil.release(msg);
    //    }
    TranslatorData response = (TranslatorData) msg;
    String producerId = "code:sessionId:002";
    MessageProducer messageProducer =
        RingBufferWorkerPoolFactory.getInstance().getMessageProducer(producerId);
    messageProducer.onData(response, ctx);
  }
}
