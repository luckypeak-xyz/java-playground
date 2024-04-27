package xyz.luckypeak.playground.productservice.app;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
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

  @Cacheable(cacheNames = "products", key = "#id")
  public Optional<Product> getProduct(String id) {
    log.info("Get product: {}", id);
    return productRepository.findById(id);
  }

  //  @Transactional(transactionManager = "mongoTransactionManager")
  @CachePut(cacheNames = "products", key = "#id")
  public Product updateProduct(String id, Product product) {
    product.setId(id);
    productRepository.save(product);
    log.info("Product updated: {}", product.getId());
    return product;
  }

  //  @Transactional(transactionManager = "mongoTransactionManager")
  @CacheEvict(cacheNames = "products", key = "#id")
  public void deleteProduct(String id) {
    productRepository.deleteById(id);
    log.info("Product deleted: {}", id);
  }
}
