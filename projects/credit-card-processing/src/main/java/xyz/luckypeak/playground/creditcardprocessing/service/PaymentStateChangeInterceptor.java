package xyz.luckypeak.playground.creditcardprocessing.service;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.Message;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.support.StateMachineInterceptorAdapter;
import org.springframework.statemachine.transition.Transition;
import org.springframework.stereotype.Component;
import xyz.luckypeak.playground.creditcardprocessing.domain.Payment;
import xyz.luckypeak.playground.creditcardprocessing.domain.PaymentEvent;
import xyz.luckypeak.playground.creditcardprocessing.domain.PaymentState;
import xyz.luckypeak.playground.creditcardprocessing.domain.repository.PaymentRepository;

@RequiredArgsConstructor
@Component
public class PaymentStateChangeInterceptor
    extends StateMachineInterceptorAdapter<PaymentState, PaymentEvent> {
  private final PaymentRepository paymentRepository;

  @Override
  public void preStateChange(
      State<PaymentState, PaymentEvent> state,
      Message<PaymentEvent> message,
      Transition<PaymentState, PaymentEvent> transition,
      StateMachine<PaymentState, PaymentEvent> stateMachine,
      StateMachine<PaymentState, PaymentEvent> rootStateMachine) {
    Optional.ofNullable(message)
        .ifPresent(
            msg -> {
              Optional.ofNullable(
                      Long.class.cast(
                          msg.getHeaders().getOrDefault(PaymentService.PAYMENT_ID_HEADER, -1L)))
                  .ifPresent(
                      paymentId -> {
                        Payment payment = paymentRepository.getReferenceById(paymentId);
                        payment.setState(state.getId());
                        paymentRepository.save(payment);
                      });
            });
  }
}
