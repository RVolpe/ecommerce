package com.ecommerce.payment.repository;

import com.ecommerce.payment.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface  PaymentRepository extends JpaRepository<PaymentEntity, Long> {
}
