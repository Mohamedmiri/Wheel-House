package com.example.Wheel_House.dto;

import jakarta.validation.constraints.NotBlank;

public class RoleDto {
    @NotBlank(message = "name must not be blank")
    private String nom;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
