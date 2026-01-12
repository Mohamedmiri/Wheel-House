package com.example.Wheel_House.model;

import com.example.Wheel_House.outile.TypeFuel;
import com.example.Wheel_House.outile.TypeVoiture;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;
@Entity
public class Voiture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCar;
    private String matricule;
    private int nombrePlace;
    private String marque;
    private String modele;
    private LocalDate dateExpirationAssurance;
    private LocalDate dateCirculation;
    private int prix;
    @Enumerated(EnumType.STRING)
    private TypeVoiture typeVoiture;
    @Enumerated(EnumType.STRING)
    private TypeFuel typeFuel;

    @OneToMany(mappedBy = "voiture")
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
