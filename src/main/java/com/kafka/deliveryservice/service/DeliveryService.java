package com.kafka.deliveryservice.service;

import com.kafka.deliveryservice.deliveryGateway.DeliveryAdapter;
import com.kafka.deliveryservice.entity.Delivery;
import com.kafka.deliveryservice.entity.UserAddress;
import com.kafka.deliveryservice.enums.DeliveryStatus;
import com.kafka.deliveryservice.repository.DeliveryRepository;
import com.kafka.deliveryservice.repository.UserAddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeliveryService {

    @Autowired
    UserAddressRepository userAddressRepository;

    @Autowired
    DeliveryRepository deliveryRepository;

    @Autowired
    DeliveryAdapter deliveryAdapter;

    public UserAddress addUserAddress(Long userId, String address, String alias) {
        var userAddress = new UserAddress(userId, address, alias);

        return userAddressRepository.save(userAddress);
    }

    public Delivery processDelivery(Long orderId, String productName, Long productCount, String address) {
        var refCode = deliveryAdapter.processDelivery(productName, productCount, address);
        var delivery = new Delivery(orderId,
                                    productName,
                                    productCount,
                                    address,
                                    DeliveryStatus.REQUESTED,
                                    refCode);

        return deliveryRepository.save(delivery);
    }

    public Delivery getDelivery(Long deliveryId) {
        return deliveryRepository.findById(deliveryId).orElseThrow();
    }

    public UserAddress getAddress(Long addressId) {
        return userAddressRepository.findById(addressId).orElseThrow();
    }

    public UserAddress getUserAddress(Long userId) {
        return userAddressRepository.findByUserId(userId).stream().findFirst().orElseThrow();
    }
}
