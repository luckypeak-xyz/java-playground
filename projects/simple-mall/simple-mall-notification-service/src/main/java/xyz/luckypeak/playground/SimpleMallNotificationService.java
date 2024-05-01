package xyz.luckypeak.playground;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;
import xyz.luckypeak.playground.event.OrderPlacedEvent;

@SpringBootApplication
@Slf4j
public class SimpleMallNotificationService {
  public static void main(String[] args) {
    SpringApplication.run(SimpleMallNotificationService.class, args);
  }

  @KafkaListener(topics = "notificationTopic")
  public void handleNotification(OrderPlacedEvent orderPlacedEvent) {
    // send out an email notification
    log.info("Received Notification for Order - {}", orderPlacedEvent.getOrderNo());
  }
}
