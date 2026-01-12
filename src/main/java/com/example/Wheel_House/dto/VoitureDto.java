package com.example.Wheel_House.dto;

import com.example.Wheel_House.model.Order;
import com.example.Wheel_House.outile.TypeFuel;
import com.example.Wheel_House.outile.TypeVoiture;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.util.List;

public class VoitureDto {

    private Long idCar;
    @NotBlank(message = "Le matricule est obligatoire")
    @Pattern(
            regexp = "^[A-Z0-9-]{4,15}$",
            message = "Le matricule doit être alphanumérique (4 à 15 caractères)"
    )
    private String matricule;

    @Min(value = 1, message = "Le nombre de places doit être au minimum 1")
    @Max(value = 9, message = "Le nombre de places ne doit pas dépasser 9")
    private int nombrePlace;

    @NotBlank(message = "La marque est obligatoire")
    private String marque;

    @NotBlank(message = "Le modèle est obligatoire")
    private String modele;

    @NotNull(message = "La date d'expiration de l'assurance est obligatoire")
    @Future(message = "La date d'expiration de l’assurance doit être dans le futur")
    private LocalDate dateExpirationAssurance;

    @NotNull(message = "La date de mise en circulation est obligatoire")
    @PastOrPresent(message = "La date de circulation doit être dans le passé ou la date actuelle")
    private LocalDate dateCirculation;

    @Min(value = 0, message = "Le prix ne peut pas être négatif")
    private int prix;

    @NotNull(message = "Le type de voiture est obligatoire")
    private TypeVoiture typeVoiture;

    @NotNull(message = "Le type de carburant est obligatoire")
    private TypeFuel typeFuel;

    private List<Order> orders;

    public Long getIdCar() {
        return idCar;
    }

    public void setIdCar(Long idCar) {
        this.idCar = idCar;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public int getNombrePlace() {
        return nombrePlace;
    }

    public void setNombrePlace(int nombrePlace) {
        this.nombrePlace = nombrePlace;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getModele() {
        return modele;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    public LocalDate getDateExpirationAssurance() {
        return dateExpirationAssurance;
    }

    public void setDateExpirationAssurance(LocalDate dateExpirationAssurance) {
        this.dateExpirationAssurance = dateExpirationAssurance;
    }

    public LocalDate getDateCirculation() {
        return dateCirculation;
    }

    public void setDateCirculation(LocalDate dateCirculation) {
        this.dateCirculation = dateCirculation;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public TypeVoiture getTypeVoiture() {
        return typeVoiture;
    }

    public void setTypeVoiture(TypeVoiture typeVoiture) {
        this.typeVoiture = typeVoiture;
    }

    public TypeFuel getTypeFuel() {
        return typeFuel;
    }

    public void setTypeFuel(TypeFuel typeFuel) {
        this.typeFuel = typeFuel;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
