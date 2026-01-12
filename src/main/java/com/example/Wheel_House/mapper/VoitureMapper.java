package com.example.Wheel_House.mapper;

import com.example.Wheel_House.dto.VoitureDto;
import com.example.Wheel_House.model.Voiture;
import org.springframework.stereotype.Component;

@Component
public class VoitureMapper {

    public VoitureDto toDto(Voiture voiture) {
        if (voiture == null) return null;

        VoitureDto dto = new VoitureDto();
        dto.setIdCar(voiture.getIdCar());
        dto.setMatricule(voiture.getMatricule());
        dto.setNombrePlace(voiture.getNombrePlace());
        dto.setMarque(voiture.getMarque());
        dto.setModele(voiture.getModele());
        dto.setDateExpirationAssurance(voiture.getDateExpirationAssurance());
        dto.setDateCirculation(voiture.getDateCirculation());
        dto.setPrix(voiture.getPrix());
        dto.setTypeVoiture(voiture.getTypeVoiture());
        dto.setTypeFuel(voiture.getTypeFuel());

        return dto;
    }

    public Voiture toEntity(VoitureDto dto) {
        if (dto == null) return null;

        Voiture voiture = new Voiture();
        voiture.setIdCar(dto.getIdCar());
        voiture.setMatricule(dto.getMatricule());
        voiture.setNombrePlace(dto.getNombrePlace());
        voiture.setMarque(dto.getMarque());
        voiture.setModele(dto.getModele());
        voiture.setDateExpirationAssurance(dto.getDateExpirationAssurance());
        voiture.setDateCirculation(dto.getDateCirculation());
        voiture.setPrix(dto.getPrix());
        voiture.setTypeVoiture(dto.getTypeVoiture());
        voiture.setTypeFuel(dto.getTypeFuel());

        return voiture;
    }
}
