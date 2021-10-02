package com.ecommerce.checkout.entity;

import lombok.*;

import javax.persistence.*;

@Entity
//@Audited
//@EntityListeners(AuditingEntityListener.class)
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CheckoutItemEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String product;

    @ManyToOne
    private CheckoutEntity checkout;
}
