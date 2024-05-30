package shippingService.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import shippingService.model.Order;
import shippingService.service.ShippingService;

@Component
@Slf4j
@RequiredArgsConstructor
public class ShippingListener {

    private final ShippingService shippingService;

    @KafkaListener(topics = "${app.kafka.payedOrdersTopic}", groupId = "${app.kafka.groupId}", containerFactory = "kafkaListenerContainerFactory")
    public void listen(Order order) {
        log.info("Received payed order: {}", order);
        shippingService.shipOrder(order);
    }
}
