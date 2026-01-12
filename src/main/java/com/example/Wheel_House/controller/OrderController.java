package com.example.Wheel_House.controller;

import com.example.Wheel_House.dto.OrderDto;
import com.example.Wheel_House.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public OrderDto createOrder(@RequestBody @Valid OrderDto dto) {
        return orderService.createOrder(dto);
    }

    @GetMapping("/{id}")
    public OrderDto getOrderById(@PathVariable Long id) {
        return orderService.getOrderById(id);
    }

    @GetMapping
    public List<OrderDto> getAllOrders() {
        return orderService.getAllOrders();
    }

    @PutMapping("/{id}")
    public OrderDto updateOrder(@PathVariable Long id,
                                @RequestBody @Valid OrderDto dto) {
        return orderService.updateOrder(id, dto);
    }

    @DeleteMapping("/{id}")
    public String deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return "Order deleted successfully";
    }
}
