package xyz.luckypeak.playground.springlearning.retrytemplatelearning;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RetryServiceTest {

    @Autowired
    private RetryService retryService;

    @Test
    void service() {
        retryService.service();
    }

    @Test
    void service2() {
    }

}