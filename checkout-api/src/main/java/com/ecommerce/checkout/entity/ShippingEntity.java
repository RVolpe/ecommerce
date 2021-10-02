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
public class ShippingEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String address;

    @Column
    private String complement;

    @Column
    private String country;

    @Column
    private String state;

    @Column
    private String cep;

    @OneToOne(mappedBy = "shipping")
    private CheckoutEntity checkout;
}
