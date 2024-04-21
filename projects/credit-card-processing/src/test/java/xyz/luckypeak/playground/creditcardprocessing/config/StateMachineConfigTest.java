package xyz.luckypeak.playground.creditcardprocessing.config;

import static org.junit.jupiter.api.Assertions.*;

import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import xyz.luckypeak.playground.creditcardprocessing.domain.PaymentEvent;
import xyz.luckypeak.playground.creditcardprocessing.domain.PaymentState;

@SpringBootTest
class StateMachineConfigTest {
  @Autowired StateMachineFactory<PaymentState, PaymentEvent> factory;

  @Test
  public void testNewStateMachine() {
    StateMachine<PaymentState, PaymentEvent> stateMachine =
        factory.getStateMachine(UUID.randomUUID());
    stateMachine.start();
    System.out.println(stateMachine.getState().toString());
    stateMachine.sendEvent(PaymentEvent.PRE_AUTHORIZE);
    System.out.println(stateMachine.getState().toString());
    stateMachine.sendEvent(PaymentEvent.PRE_AUTH_APPROVED);
    System.out.println(stateMachine.getState().toString());
    stateMachine.sendEvent(PaymentEvent.PRE_AUTH_DECLINED);
    System.out.println(stateMachine.getState().toString());
  }
}
