package shippingService.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import shippingService.model.Order;
import shippingService.service.ShippingService;

@RestController
@RequestMapping("/api/v1/shipping")
@RequiredArgsConstructor
public class ShippingController {

    private final ShippingService shippingService;

    @PostMapping
    public ResponseEntity<String> shipOrder(@RequestBody Order order) {
        shippingService.shipOrder(order);
        return ResponseEntity.ok("Order shipped successfully");
    }
}