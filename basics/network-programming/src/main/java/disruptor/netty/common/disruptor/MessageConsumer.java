package disruptor.netty.common.disruptor;

import com.lmax.disruptor.WorkHandler;
import disruptor.netty.common.entity.TranslatorDataWrapper;

public abstract class MessageConsumer implements WorkHandler<TranslatorDataWrapper> {

  protected String consumerId;

  public MessageConsumer(String consumerId) {
    this.consumerId = consumerId;
  }

  public String getConsumerId() {
    return consumerId;
  }

  public void setConsumerId(String consumerId) {
    this.consumerId = consumerId;
  }
}
