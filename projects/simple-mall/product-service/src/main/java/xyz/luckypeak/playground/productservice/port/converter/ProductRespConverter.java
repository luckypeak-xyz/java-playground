package xyz.luckypeak.playground.productservice.port.converter;

import java.util.List;
import org.mapstruct.Mapper;
import xyz.luckypeak.playground.productservice.domain.model.Product;
import xyz.luckypeak.playground.productservice.port.dto.ProductResp;

@Mapper(componentModel = "spring")
public interface ProductRespConverter {
  List<ProductResp> fromDomain(List<Product> products);
}
