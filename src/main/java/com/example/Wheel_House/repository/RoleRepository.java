package com.example.Wheel_House.repository;

import com.example.Wheel_House.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {

    Role findByNom(String nom);
}
