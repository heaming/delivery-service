package com.kafka.deliveryservice.repository;

import com.kafka.deliveryservice.entity.Delivery;
import com.kafka.deliveryservice.enums.DeliveryStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeliveryRepository extends JpaRepository<Delivery, Long> {
    List<Delivery> findAllByOrderId(Long orderId);
    List<Delivery> findAllByDeliveryStatus(DeliveryStatus deliveryStatus);
}
