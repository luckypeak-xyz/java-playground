package xyz.luckypeak.playground.simplemallinventoryservice.adaptor.repository.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import xyz.luckypeak.playground.simplemallinventoryservice.domain.model.Inventory;

import java.util.List;

public interface InventoryMapper extends BaseMapper<Inventory> {
  Inventory findBySkuCode(@Param("skuCode") String skuCode);

  List<Inventory> findBySkuCodeIn(@Param("skuCodes") List<String> skuCodes);
}
