package xyz.luckypeak.playground.simplemallorderservice.dto.converter;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import xyz.luckypeak.playground.simplemallorderservice.dto.OrderReq;
import xyz.luckypeak.playground.simplemallorderservice.model.Order;

@Mapper(componentModel = "spring")
public interface OrderReqConverter {
  @Mapping(target = "orderLineItemsList", source = "orderReq.orderLineItemsDtoList")
  Order toOrder(OrderReq orderReq);
}
