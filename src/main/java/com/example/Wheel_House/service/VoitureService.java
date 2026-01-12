package com.example.Wheel_House.service;

import com.example.Wheel_House.dto.VoitureDto;
import com.example.Wheel_House.mapper.VoitureMapper;
import com.example.Wheel_House.model.Voiture;
import com.example.Wheel_House.repository.OrderRepository;
import com.example.Wheel_House.repository.VoitureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VoitureService {
    @Autowired
    VoitureRepository voitureRepository;
    @Autowired
    VoitureMapper voitureMapper;
    @Autowired
    OrderRepository orderRepository;

    public VoitureDto createVoiture(VoitureDto dto) {
        Voiture voiture = voitureMapper.toEntity(dto);
        Voiture saved = voitureRepository.save(voiture);
        return voitureMapper.toDto(saved);
    }

    public VoitureDto getVoitureById(Long id) {
        Voiture voiture = voitureRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Voiture not found with id: " + id));

        return voitureMapper.toDto(voiture);
    }

    public List<VoitureDto> getAllVoitures() {
        return voitureRepository.findAll()
                .stream()
                .map(voitureMapper::toDto)
                .toList();
    }
    public Page<VoitureDto> getVoituresPage(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return voitureRepository.findAll(pageable)
                .map(voitureMapper::toDto);
    }


    public VoitureDto updateVoiture(Long id, VoitureDto dto) {
        Voiture exisrte = voitureRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Voiture not found with id: " + id));

        exisrte.setMatricule(dto.getMatricule());
        exisrte.setNombrePlace(dto.getNombrePlace());
        exisrte.setMarque(dto.getMarque());
        exisrte.setModele(dto.getModele());
        exisrte.setDateExpirationAssurance(dto.getDateExpirationAssurance());
        exisrte.setDateCirculation(dto.getDateCirculation());
        exisrte.setPrix(dto.getPrix());
        exisrte.setTypeVoiture(dto.getTypeVoiture());
        exisrte.setTypeFuel(dto.getTypeFuel());

        Voiture saved = voitureRepository.save(exisrte);
        return voitureMapper.toDto(saved);
    }

    public void deleteVoiture(Long id) {
        Voiture voiture=voitureRepository.findById(id)
                .orElseThrow(()->new RuntimeException("aucun voiture ce trouve avec ce Id :"+id));

        if(orderRepository.existsByVoiture(voiture)){
            throw new RuntimeException("Impossible de supprimer cette voiture : elle est utilisée dans des orders.");
        }

        voitureRepository.delete(voiture);
    }
}
