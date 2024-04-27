package xyz.luckypeak.playground.productservice.port.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import xyz.luckypeak.playground.productservice.app.ProductService;
import xyz.luckypeak.playground.productservice.port.converter.ProductReqConverter;
import xyz.luckypeak.playground.productservice.port.converter.ProductRespConverter;
import xyz.luckypeak.playground.productservice.port.dto.ProductReq;
import xyz.luckypeak.playground.productservice.port.dto.ProductResp;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
  private final ProductService productService;
  private final ProductReqConverter productReqConverter;
  private final ProductRespConverter productRespConverter;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public void createProduct(@RequestBody ProductReq productReq) {
    productService.createProduct(productReqConverter.toDomain(productReq));
  }

  @GetMapping
  @ResponseBody
  public List<ProductResp> getAllProducts() {
    return productRespConverter.fromProducts(productService.getAllProducts());
  }

  @GetMapping("/{id}")
  @ResponseBody
  public ProductResp getProduct(@PathVariable String id) {
    return productRespConverter.fromProduct(productService.getProduct(id).orElse(null));
  }

  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public void updateProduct(@PathVariable String id, @RequestBody ProductReq productReq) {
    productService.updateProduct(id, productReqConverter.toDomain(productReq));
  }
}
