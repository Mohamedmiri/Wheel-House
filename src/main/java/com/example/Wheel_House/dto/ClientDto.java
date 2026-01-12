package com.example.Wheel_House.dto;

import com.example.Wheel_House.model.Order;
import com.example.Wheel_House.outile.Genre;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;
import java.util.List;

public class ClientDto {

    private Long id;

    @NotBlank(message = "Le nom ne doit pas être vide")
    private String nomClient;

    @NotBlank(message = "Le prénom ne doit pas être vide")
    private String prenomClient;

    @NotBlank(message = "Le numéro de téléphone ne doit pas être vide")
    @Pattern(
            regexp = "^[0-9]{8,15}$",
            message = "Le numéro de téléphone doit contenir uniquement des chiffres (8 à 15 caractères)"
    )
    private String telephone;

    @NotBlank(message = "Le CIN ne doit pas être vide")
    @Pattern(
            regexp = "^[A-Za-z0-9]{4,12}$",
            message = "Le CIN doit être alphanumérique (4 à 12 caractères)"
    )
    private String cin;

    @NotNull(message = "La date de naissance est obligatoire")
    private LocalDate dateNaissance;

    @NotNull(message = "Le genre est obligatoire")
    private Genre genre;

    @NotBlank(message = "La ville ne doit pas être vide")
    private String ville;

    @NotBlank(message = "Le pays ne doit pas être vide")
    private String paye;

    private List<Order> orders;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomClient() {
        return nomClient;
    }

    public void setNomClient(String nomClient) {
        this.nomClient = nomClient;
    }

    public String getPrenomClient() {
        return prenomClient;
    }

    public void setPrenomClient(String prenomClient) {
        this.prenomClient = prenomClient;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public LocalDate getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(LocalDate dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getPaye() {
        return paye;
    }

    public void setPaye(String paye) {
        this.paye = paye;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
