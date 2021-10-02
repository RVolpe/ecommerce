package com.ecommerce.checkout.listener;

import com.ecommerce.checkout.entity.CheckoutEntity;
import com.ecommerce.checkout.repository.CheckoutRepository;
import com.ecommerce.checkout.streaming.PaymentPaidSink;
import com.ecommerce.payment.event.PaymentCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentPaidListener {

    //private final CheckoutService checkoutService;
    private final CheckoutRepository checkoutRepository;

    @StreamListener(PaymentPaidSink.INPUT)
    public void handler(PaymentCreatedEvent eventPayment) {

        final CheckoutEntity checkoutEntity = checkoutRepository
                .findByCode(eventPayment.getCheckoutCode().toString()).orElseThrow();
        checkoutEntity.setStatus(CheckoutEntity.Status.APPROVED);
        checkoutRepository.save(checkoutEntity);
        //checkoutService.updateStatus(eventPayment.getCheckoutCode().toString(), CheckoutEntity.Status.APPROVED);
    }
}
