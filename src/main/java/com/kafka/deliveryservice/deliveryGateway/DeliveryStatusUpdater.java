package com.kafka.deliveryservice.deliveryGateway;

import com.kafka.deliveryservice.enums.DeliveryStatus;
import com.kafka.deliveryservice.repository.DeliveryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DeliveryStatusUpdater {

    @Autowired
    DeliveryRepository deliveryRepository;

    @Scheduled(fixedDelay = 10000)
    public void deliveryStatusUpdate() {
        log.info("delivery status updated...");

        var inDeliveryList = deliveryRepository.findAllByDeliveryStatus(DeliveryStatus.IN_DELIVERY);
        inDeliveryList.forEach(delivery -> {
            delivery.deliveryStatus = DeliveryStatus.COMPLETED;
            deliveryRepository.save(delivery);
        });

        var requestedList = deliveryRepository.findAllByDeliveryStatus(DeliveryStatus.REQUESTED);
        requestedList.forEach(delivery -> {
            delivery.deliveryStatus = DeliveryStatus.IN_DELIVERY;
            deliveryRepository.save(delivery);
        });
    }
}
