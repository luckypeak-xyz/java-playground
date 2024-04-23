package xyz.luckypeak.playground.productservice.app;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import xyz.luckypeak.playground.productservice.domain.model.Product;
import xyz.luckypeak.playground.productservice.domain.repository.ProductRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
  private final ProductRepository productRepository;

  public void createProduct(Product product) {
    productRepository.save(product);
    log.info("Product created: {}", product.getId());
  }

  public List<Product> getAllProducts() {
    return productRepository.findAll();
  }
}
