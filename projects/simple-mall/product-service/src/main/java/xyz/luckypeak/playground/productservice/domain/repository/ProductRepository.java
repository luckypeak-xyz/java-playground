package xyz.luckypeak.playground.productservice.domain.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import xyz.luckypeak.playground.productservice.domain.model.Product;

public interface ProductRepository extends MongoRepository<Product, String> {}
