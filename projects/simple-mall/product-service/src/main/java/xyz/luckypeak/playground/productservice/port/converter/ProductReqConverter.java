package xyz.luckypeak.playground.productservice.port.converter;

import org.mapstruct.Mapper;
import xyz.luckypeak.playground.productservice.domain.model.Product;
import xyz.luckypeak.playground.productservice.port.dto.ProductReq;

@Mapper(componentModel = "spring")
public interface ProductReqConverter {
  Product toDomain(ProductReq productReq);
}
