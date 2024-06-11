package com.kafka.deliveryservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Entity
@NoArgsConstructor
@Table(indexes = {@Index(name = "idx_userId", columnList = "userId")})
public class UserAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public Long userId;
    public String address;
    public String alias;

    public UserAddress(Long userId, String address, String alias) {
        this.userId = userId;
        this.address = address;
        this.alias = alias;
    }
}
