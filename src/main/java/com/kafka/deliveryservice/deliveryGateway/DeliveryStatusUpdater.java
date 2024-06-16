package com.kafka.deliveryservice.deliveryGateway;

import blackfriday.protobuf.EdaMessage;
import com.kafka.deliveryservice.entity.Delivery;
import com.kafka.deliveryservice.enums.DeliveryStatus;
import com.kafka.deliveryservice.repository.DeliveryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DeliveryStatusUpdater {

    @Autowired
    DeliveryRepository deliveryRepository;

    @Autowired
    private KafkaTemplate<String, byte[]> kafkaTemplate;

    @Scheduled(fixedDelay = 10000)
    public void deliveryStatusUpdate() {
        log.info("delivery status updated...");

        var inDeliveryList = deliveryRepository.findAllByDeliveryStatus(DeliveryStatus.IN_DELIVERY);
        inDeliveryList.forEach(delivery -> {
            delivery.deliveryStatus = DeliveryStatus.COMPLETED;
            deliveryRepository.save(delivery);

            publishStatusChange(delivery);
        });

        var requestedList = deliveryRepository.findAllByDeliveryStatus(DeliveryStatus.REQUESTED);
        requestedList.forEach(delivery -> {
            delivery.deliveryStatus = DeliveryStatus.IN_DELIVERY;
            deliveryRepository.save(delivery);

            publishStatusChange(delivery);
        });
    }

    private void publishStatusChange(Delivery delivery) {
        var deliveryStatusMessage = EdaMessage.DeliveryStatusUpdateV1.newBuilder()
                .setOrderId(delivery.orderId)
                .setDeliveryId(delivery.id)
                .setDeliveryStatus(delivery.deliveryStatus.toString())
                .build();

        kafkaTemplate.send("delivery_status_update", deliveryStatusMessage.toByteArray());
    }
}
