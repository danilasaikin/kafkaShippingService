package shippingService.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import shippingService.model.Order;

@Service
@Slf4j
public class ShippingService {

    private final KafkaTemplate<String, Order> kafkaTemplate;
    private final String sentOrdersTopic;

    public ShippingService(
            KafkaTemplate<String, Order> kafkaTemplate,
            @Value("${app.kafka.sentOrdersTopic}")
            String sentOrdersTopic) {
        this.kafkaTemplate = kafkaTemplate;
        this.sentOrdersTopic = sentOrdersTopic;
    }

    public void shipOrder(Order order) {
        log.info("Shipping order: {}", order);
        order.setStatus("SHIPPED");
        kafkaTemplate.send(sentOrdersTopic, order);
    }
}