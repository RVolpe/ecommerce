package com.ecommerce.checkout.service;

import com.ecommerce.checkout.entity.CheckoutEntity;
import com.ecommerce.checkout.event.CheckoutCreatedEvent;
import com.ecommerce.checkout.repository.CheckoutRepository;
import com.ecommerce.checkout.resource.checkout.CheckoutRequest;
import com.ecommerce.checkout.streaming.CheckoutCreatedSource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class CheckoutServiceImpl implements CheckoutService {

    private final CheckoutRepository checkoutRepository;
    private final CheckoutCreatedSource checkoutCreatedSource;
    //private final UUIDUtil uuidUtil;

    @Override
    public Optional<CheckoutEntity> create(CheckoutRequest checkoutRequest) {
        final CheckoutEntity checkoutEntity = CheckoutEntity.builder()
                .code(UUID.randomUUID().toString())
                .status(CheckoutEntity.Status.CREATED)
                .build();
        final CheckoutEntity entity = checkoutRepository.save(checkoutEntity);

        final CheckoutCreatedEvent checkoutCreatedEvent = CheckoutCreatedEvent.newBuilder()
                .setCheckoutCode(entity.getCode())
                .setStatus(entity.getStatus().name())
                .build();
        checkoutCreatedSource.output().send(MessageBuilder.withPayload(checkoutCreatedEvent).build());
        return Optional.of(entity);
    }

    @Override
    public Optional<CheckoutEntity> updateStatus(String checkoutCode, CheckoutEntity.Status status) {
        return Optional.empty();
    }
}
