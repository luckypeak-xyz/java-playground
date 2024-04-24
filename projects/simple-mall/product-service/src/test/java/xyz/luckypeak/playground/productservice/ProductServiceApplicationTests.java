package xyz.luckypeak.playground.productservice;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.math.BigDecimal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Testcontainers;
import xyz.luckypeak.playground.productservice.domain.repository.ProductRepository;
import xyz.luckypeak.playground.productservice.port.dto.ProductReq;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
class ProductServiceApplicationTests {
  static MongoDBContainer MONGO_DB_CONTAINER = new MongoDBContainer("mongo:4.4.2");

  static {
    MONGO_DB_CONTAINER.start();
  }

  @Autowired private MockMvc mockMvc;
  @Autowired private ObjectMapper objectMapper;
  @Autowired private ProductRepository productRepository;

  @DynamicPropertySource
  static void setProperties(DynamicPropertyRegistry dynamicPropertyRegistry) {
    dynamicPropertyRegistry.add("spring.data.mongodb.uri", MONGO_DB_CONTAINER::getReplicaSetUrl);
  }

  @Test
  public void shouldCreateProduct() throws Exception {
    ProductReq productReq = getProductReq();
    String productReqStr = objectMapper.writeValueAsString(productReq);
    mockMvc
        .perform(
            MockMvcRequestBuilders.post("/api/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(productReqStr))
        .andExpect(status().isCreated());
    Assertions.assertEquals(1, productRepository.findAll().size());
  }

  private ProductReq getProductReq() {
    return ProductReq.builder()
        .name("iPhone 15")
        .description("iPhone 15")
        .price(BigDecimal.valueOf(1000))
        .build();
  }
}
