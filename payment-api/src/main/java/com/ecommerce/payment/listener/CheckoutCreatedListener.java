package com.ecommerce.payment.listener;

import com.ecommerce.checkout.event.CheckoutCreatedEvent;
import com.ecommerce.payment.entity.PaymentEntity;
import com.ecommerce.payment.service.PaymentCreatedEvent;
import com.ecommerce.payment.service.PaymentService;
import com.ecommerce.payment.streaming.CheckoutProcessor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class CheckoutCreatedListener {

    final private CheckoutProcessor checkoutProcessor;

    private final PaymentService paymentService;

    @StreamListener(CheckoutProcessor.INPUT)
    public void handler(CheckoutCreatedEvent eventCheckout) {
        log.info("checkoutCreatedEvent={}", eventCheckout);
        //Processa pagamento gateway
        //Salvar dados do pagamento
        //Enviar o evento de pagamento processado

        final PaymentEntity paymentEntity = paymentService.create(eventCheckout).orElseThrow();
        final PaymentCreatedEvent paymentCreatedEvent = PaymentCreatedEvent.newBuilder()
                .setCheckoutCode(paymentEntity.getCheckoutCode())
                .setPaymentCode(paymentEntity.getCode())
                .build();
        checkoutProcessor.output().send(MessageBuilder.withPayload(paymentCreatedEvent).build());
    }
}
