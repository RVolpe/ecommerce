package com.ecommerce.payment.service;

import com.ecommerce.checkout.event.CheckoutCreatedEvent;
import com.ecommerce.payment.entity.PaymentEntity;

import java.util.Optional;

public interface PaymentService {

    Optional<PaymentEntity> create(CheckoutCreatedEvent checkoutCreatedEvent);
}
