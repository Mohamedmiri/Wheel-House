package com.example.Wheel_House.dto;

import com.example.Wheel_House.model.Client;
import com.example.Wheel_House.model.User;
import com.example.Wheel_House.model.Voiture;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public class OrderDto {
    private Long idOrder;
    @NotNull(message = "La date de début est obligatoire")
    @FutureOrPresent(message = "La date de début doit être aujourd’hui ou une date future")
    private LocalDate dateDebut;

    @NotNull(message = "La date de fin est obligatoire")
    @Future(message = "La date de fin doit être une date future")
    private LocalDate dateFin;

    @NotNull(message = "La date de location est obligatoire")
    @PastOrPresent(message = "La date de location doit être aujourd’hui ou une date passée")
    private LocalDate dateLocation;

    @NotNull(message = "La date de création est obligatoire")
    @PastOrPresent(message = "La date de création doit être dans le passé ou aujourd’hui")
    private LocalDate dateCreation;

    @Min(value = 0, message = "Le prix total ne peut pas être négatif")
    private int prixTotal;
    private User user;
    private Client client;
    private Voiture voiture;

    public Long getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(Long idOrder) {
        this.idOrder = idOrder;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    public LocalDate getDateLocation() {
        return dateLocation;
    }

    public void setDateLocation(LocalDate dateLocation) {
        this.dateLocation = dateLocation;
    }

    public LocalDate getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDate dateCreation) {
        this.dateCreation = dateCreation;
    }

    public int getPrixTotal() {
        return prixTotal;
    }

    public void setPrixTotal(int prixTotal) {
        this.prixTotal = prixTotal;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Voiture getCar() {
        return voiture;
    }

    public void setCar(Voiture voiture) {
        this.voiture = voiture;
    }

    @AssertTrue(message = "End date must be after start date")
    public boolean isValidDates() {
        if (dateDebut == null || dateFin == null) return true;
        return dateFin.isAfter(dateDebut);
    }
}
