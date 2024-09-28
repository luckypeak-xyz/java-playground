package disruptor.netty.client;

import disruptor.netty.common.disruptor.MessageConsumer;
import disruptor.netty.common.entity.TranslatorData;
import disruptor.netty.common.entity.TranslatorDataWrapper;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.ReferenceCountUtil;

public class MessageConsumerImpl4Client extends MessageConsumer {
  public MessageConsumerImpl4Client(String consumerId) {
    super(consumerId);
  }

  public void onEvent(TranslatorDataWrapper event) throws Exception {
    TranslatorData response = event.getData();
    ChannelHandlerContext ctx = event.getCtx();
    // 业务逻辑处理:
    try {
      System.err.println(
          "Client端: id= "
              + response.getId()
              + ", name= "
              + response.getName()
              + ", message= "
              + response.getMessage());
    } finally {
      ReferenceCountUtil.release(response);
    }
  }
}
