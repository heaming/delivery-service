package com.kafka.deliveryservice.deliveryGateway;

public interface DeliveryAdapter {
    Long processDelivery(String productName, Long productCount, String address);
}
