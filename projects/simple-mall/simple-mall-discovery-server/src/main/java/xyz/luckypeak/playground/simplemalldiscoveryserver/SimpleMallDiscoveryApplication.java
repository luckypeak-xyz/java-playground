package xyz.luckypeak.playground.simplemalldiscoveryserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class SimpleMallDiscoveryApplication {
  public static void main(String[] args) {
    SpringApplication.run(SimpleMallDiscoveryApplication.class, args);
  }
}
