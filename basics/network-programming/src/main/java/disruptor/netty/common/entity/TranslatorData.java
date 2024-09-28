package disruptor.netty.common.entity;

import java.io.Serializable;
import lombok.Data;

@Data
public class TranslatorData implements Serializable {
  private String id;
  private String name;
  private String message; // 传输消息体内容
}
