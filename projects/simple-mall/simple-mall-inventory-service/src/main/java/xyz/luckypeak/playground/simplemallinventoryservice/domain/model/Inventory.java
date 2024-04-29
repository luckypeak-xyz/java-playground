package xyz.luckypeak.playground.simplemallinventoryservice.domain.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("`t_inventory`")
public class Inventory {
  @TableId(type = IdType.AUTO)
  private Long id;

  private String skuCode;
  private Integer quantity;
}
