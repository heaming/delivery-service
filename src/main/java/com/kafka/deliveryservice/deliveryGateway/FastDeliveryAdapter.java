package com.kafka.deliveryservice.deliveryGateway;

import org.springframework.stereotype.Service;

@Service
public class FastDeliveryAdapter implements DeliveryAdapter{
    @Override
    public Long processDelivery(String productName, Long productCount, String address) {
        // delivery process

        return 111111L;
    }
}
