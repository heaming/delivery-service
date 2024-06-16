package com.kafka.deliveryservice.controller;

import com.kafka.deliveryservice.dto.RegisterAddressDto;
import com.kafka.deliveryservice.entity.Delivery;
import com.kafka.deliveryservice.entity.UserAddress;
import com.kafka.deliveryservice.service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/delivery")
public class DeliveryController {

    @Autowired
    DeliveryService deliveryService;

    @PostMapping("/address")
    public UserAddress registerAddress(@RequestBody RegisterAddressDto request) {
        return deliveryService.addUserAddress(request.userId, request.address, request.alias);
    }

    @GetMapping("/{deliveryId}")
    public Delivery getDelivery(@PathVariable Long deliveryId) {
        return deliveryService.getDelivery(deliveryId);
    }

    @GetMapping("/address/{addressId}")
    public UserAddress getAddress(@PathVariable Long addressId) {
        return deliveryService.getAddress(addressId);
    }

    @GetMapping("/users/{userId}/first-address")
    public UserAddress getUserAddress(@PathVariable Long userId) {
        return deliveryService.getUserAddress(userId);
    }
}
