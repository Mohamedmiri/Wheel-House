package com.example.Wheel_House.repository;

import com.example.Wheel_House.model.Client;
import com.example.Wheel_House.model.Order;
import com.example.Wheel_House.model.User;
import com.example.Wheel_House.model.Voiture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
    List<Order> findByClient(Client client);

    List<Order> findByUser(User user);

    List<Order> findByVoiture(Voiture voiture);

    List<Order> findByDateDebutBetween(LocalDate debut, LocalDate fin);

    boolean existsByClient(Client client);
    boolean existsByUser(User user);
    boolean existsByVoiture(Voiture voiture);

}
