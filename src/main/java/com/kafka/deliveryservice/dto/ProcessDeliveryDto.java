package com.kafka.deliveryservice.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

public class ProcessDeliveryDto {
    public Long orderId;
    public String productName;
    public Long productCount;
    public String address;
}
