package com.example.Wheel_House.service;

import com.example.Wheel_House.controller.ReservationConflictException;
import com.example.Wheel_House.dto.OrderDto;
import com.example.Wheel_House.mapper.OrderMapper;
import com.example.Wheel_House.model.Order;
import com.example.Wheel_House.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;
    @Autowired
    OrderMapper orderMapper;

    public OrderDto createOrder(OrderDto dto) {

        if (dto.getDateDebut() == null || dto.getDateFin() == null) {
            throw new RuntimeException("dateDebut et dateFin sont obligatoires");
        }
        if (!dto.getDateFin().isAfter(dto.getDateDebut())) {
            throw new RuntimeException("dateFin doit être strictement supérieure à dateDebut");
        }
        if (dto.getCar() == null || dto.getCar().getIdCar() == null) {
            throw new RuntimeException("car.idCar est obligatoire");
        }

        Long carId = dto.getCar().getIdCar();
        boolean dejaReserve = orderRepository.findAll().stream()
                .filter(o -> o.getCar() != null && o.getCar().getIdCar() != null)
                .filter(o -> o.getCar().getIdCar().equals(carId))
                .anyMatch(o -> overlap(dto.getDateDebut(), dto.getDateFin(), o.getDateDebut(), o.getDateFin()));

        if (dejaReserve) {
            throw new RuntimeException("Impossible : voiture déjà réservée pendant cette période.");
        }

        Order order = orderMapper.toEntity(dto);
        Order saved = orderRepository.save(order);
        return orderMapper.toDto(saved);
    }
    private boolean overlap(LocalDate newStart, LocalDate newEnd,
                            LocalDate existingStart, LocalDate existingEnd) {
        return !(newStart.isAfter(existingEnd) || newEnd.isBefore(existingStart));
    }

    public OrderDto getOrderById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + id));

        return orderMapper.toDto(order);
    }

    public List<OrderDto> getAllOrders() {
        return orderRepository.findAll()
                .stream()
                .map(orderMapper::toDto)
                .toList();
    }


    public OrderDto updateOrder(Long id, OrderDto dto) {

        Order existe = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + id));

        if (dto.getDateDebut() == null || dto.getDateFin() == null) {
            throw new RuntimeException("dateDebut et dateFin sont obligatoires");
        }
        if (!dto.getDateFin().isAfter(dto.getDateDebut())) {
            throw new RuntimeException("dateFin doit être strictement supérieure à dateDebut");
        }
        if (dto.getCar() == null || dto.getCar().getIdCar() == null) {
            throw new RuntimeException("car.idCar est obligatoire");
        }

        Long carId = dto.getCar().getIdCar();

        boolean dejaReserve = orderRepository.findAll().stream()
                .filter(o -> !o.getIdOrder().equals(id))
                .filter(o -> o.getCar() != null && o.getCar().getIdCar() != null)
                .filter(o -> o.getCar().getIdCar().equals(carId))
                .anyMatch(o -> overlap(dto.getDateDebut(), dto.getDateFin(), o.getDateDebut(), o.getDateFin()));

        if (dejaReserve) {
            throw new ReservationConflictException("Impossible : voiture déjà réservée pendant cette période.");

        }

        existe.setDateDebut(dto.getDateDebut());
        existe.setDateFin(dto.getDateFin());
        existe.setDateLocation(dto.getDateLocation());
        existe.setPrixTotal(dto.getPrixTotal());
        existe.setUser(dto.getUser());
        existe.setCar(dto.getCar());

        Order saved = orderRepository.save(existe);
        return orderMapper.toDto(saved);
    }



    public void deleteOrder(Long id) {
        if (!orderRepository.existsById(id)) {
            throw new RuntimeException("Order not found with id: " + id);
        }
        orderRepository.deleteById(id);
    }


}
