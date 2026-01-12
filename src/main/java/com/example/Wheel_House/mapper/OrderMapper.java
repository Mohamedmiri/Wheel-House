package com.example.Wheel_House.mapper;

import com.example.Wheel_House.dto.OrderDto;
import com.example.Wheel_House.model.Order;
import com.example.Wheel_House.repository.ClientRepository;
import com.example.Wheel_House.repository.UserRepository;
import com.example.Wheel_House.repository.VoitureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {


   @Autowired
   UserRepository userRepository;
   @Autowired
   ClientRepository clientRepository;
   @Autowired
   VoitureRepository voitureRepository;


    public OrderMapper(UserRepository userRepository,
                       ClientRepository clientRepository,
                       VoitureRepository voitureRepository) {
        this.userRepository = userRepository;
        this.clientRepository = clientRepository;
        this.voitureRepository = voitureRepository;
    }

    public OrderDto toDto(Order order) {
        if (order == null) return null;

        OrderDto dto = new OrderDto();
        dto.setIdOrder(order.getIdOrder());
        dto.setDateDebut(order.getDateDebut());
        dto.setDateFin(order.getDateFin());
        dto.setDateLocation(order.getDateLocation());
        dto.setDateCreation(order.getDateCreation());
        dto.setPrixTotal(order.getPrixTotal());

        dto.setUser(order.getUser());
        dto.setClient(order.getClient());
        dto.setCar(order.getCar());

        return dto;
    }


    public Order toEntity(OrderDto dto) {
        if (dto == null) return null;

        Order order = new Order();
        order.setIdOrder(dto.getIdOrder());
        order.setDateDebut(dto.getDateDebut());
        order.setDateFin(dto.getDateFin());
        order.setDateLocation(dto.getDateLocation());
        order.setDateCreation(dto.getDateCreation());
        order.setPrixTotal(dto.getPrixTotal());

        order.setUser(dto.getUser());
        order.setClient(dto.getClient());
        order.setCar(dto.getCar());

        return order;
    }
}
