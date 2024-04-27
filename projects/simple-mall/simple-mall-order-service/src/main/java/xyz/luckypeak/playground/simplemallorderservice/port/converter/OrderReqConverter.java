package xyz.luckypeak.playground.simplemallorderservice.port.converter;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import xyz.luckypeak.playground.simplemallorderservice.domain.model.Order;
import xyz.luckypeak.playground.simplemallorderservice.port.dto.OrderReq;

@Mapper(componentModel = "spring")
public interface OrderReqConverter {
  @Mapping(target = "orderLineItemsList", source = "orderReq.orderLineItemsDtoList")
  Order toOrder(OrderReq orderReq);
}
