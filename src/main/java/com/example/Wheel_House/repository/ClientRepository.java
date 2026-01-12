package com.example.Wheel_House.repository;

import com.example.Wheel_House.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client,Long> {
    Client findByCin(String cin);
    Client findByTelephone(String telephone);
}
