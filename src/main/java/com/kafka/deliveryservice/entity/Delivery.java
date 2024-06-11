package com.kafka.deliveryservice.entity;

import com.kafka.deliveryservice.enums.DeliveryStatus;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Table(indexes = {@Index(name = "idx_orderId", columnList = "orderId")})
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public Long orderId;
    public String productName;
    public Long productCount;
    public String address;
    public DeliveryStatus deliveryStatus;
    public Long referenceCode;

    public Delivery(Long orderId, String productName, Long productCount, String address, DeliveryStatus deliveryStatus, Long referenceCode) {
        this.orderId = orderId;
        this.productName = productName;
        this.productCount = productCount;
        this.address = address;
        this.deliveryStatus = deliveryStatus;
        this.referenceCode = referenceCode;

    }
}
