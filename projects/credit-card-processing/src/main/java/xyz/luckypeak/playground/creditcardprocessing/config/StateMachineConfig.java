package xyz.luckypeak.playground.creditcardprocessing.config;

import java.util.EnumSet;
import java.util.Random;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;
import xyz.luckypeak.playground.creditcardprocessing.domain.PaymentEvent;
import xyz.luckypeak.playground.creditcardprocessing.domain.PaymentState;
import xyz.luckypeak.playground.creditcardprocessing.service.PaymentService;

@Configuration
@EnableStateMachineFactory
@Slf4j
public class StateMachineConfig extends StateMachineConfigurerAdapter<PaymentState, PaymentEvent> {
  @Override
  public void configure(StateMachineStateConfigurer<PaymentState, PaymentEvent> states)
      throws Exception {
    states
        .withStates()
        .initial(PaymentState.NEW)
        .states(EnumSet.allOf(PaymentState.class))
        .end(PaymentState.AUTH)
        .end(PaymentState.PRE_AUTH_ERROR)
        .end(PaymentState.AUTH_ERROR);
  }

  @Override
  public void configure(StateMachineTransitionConfigurer<PaymentState, PaymentEvent> transitions)
      throws Exception {
    transitions
        .withExternal()
        .source(PaymentState.NEW)
        .target(PaymentState.NEW)
        .event(PaymentEvent.PRE_AUTHORIZE)
        .action(preAuthAction())
        .and()
        .withExternal()
        .source(PaymentState.NEW)
        .target(PaymentState.PRE_AUTH)
        .event(PaymentEvent.PRE_AUTH_APPROVED)
        .and()
        .withExternal()
        .source(PaymentState.NEW)
        .target(PaymentState.PRE_AUTH_ERROR)
        .event(PaymentEvent.PRE_AUTH_DECLINED);
  }

  @Override
  public void configure(StateMachineConfigurationConfigurer<PaymentState, PaymentEvent> config)
      throws Exception {
    StateMachineListenerAdapter<PaymentState, PaymentEvent> listenerAdapter =
        new StateMachineListenerAdapter<>() {
          @Override
          public void stateChanged(
              State<PaymentState, PaymentEvent> from, State<PaymentState, PaymentEvent> to) {
            log.info("state changed: [{} -> {}]", from, to);
          }
        };
    config.withConfiguration().listener(listenerAdapter);
  }

  public Action<PaymentState, PaymentEvent> preAuthAction() {
    return new Action<PaymentState, PaymentEvent>() {
      @Override
      public void execute(StateContext<PaymentState, PaymentEvent> context) {
        log.info("preAuth was called");

        if (new Random().nextInt(10) < 8) {
          log.info("preAuth approved");
          context
              .getStateMachine()
              .sendEvent(
                  MessageBuilder.withPayload(PaymentEvent.PRE_AUTH_APPROVED)
                      .setHeader(
                          PaymentService.PAYMENT_ID_HEADER,
                          context.getMessageHeader(PaymentService.PAYMENT_ID_HEADER))
                      .build());
        } else {
          log.info("preAuth declined");
          context
              .getStateMachine()
              .sendEvent(
                  MessageBuilder.withPayload(PaymentEvent.PRE_AUTH_DECLINED)
                      .setHeader(
                          PaymentService.PAYMENT_ID_HEADER,
                          context.getMessageHeader(PaymentService.PAYMENT_ID_HEADER))
                      .build());
        }
      }
    };
  }
}
