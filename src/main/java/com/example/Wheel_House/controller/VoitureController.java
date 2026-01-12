package com.example.Wheel_House.controller;

import com.example.Wheel_House.dto.VoitureDto;
import com.example.Wheel_House.service.VoitureService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/voitures")
public class VoitureController {

    @Autowired
    private VoitureService voitureService;

    @PostMapping
    public VoitureDto createVoiture(@RequestBody @Valid VoitureDto dto) {
        return voitureService.createVoiture(dto);
    }

    @GetMapping("/{id}")
    public VoitureDto getVoitureById(@PathVariable Long id) {
        return voitureService.getVoitureById(id);
    }

    @GetMapping
    public Page<VoitureDto> getVoituresPage(
            @RequestParam int page,
            @RequestParam int size
    ) {
        return voitureService.getVoituresPage(page, size);
    }


    @GetMapping("/all")
    public List<VoitureDto> getAllVoitures() {
        return voitureService.getAllVoitures();
    }

    @PutMapping("/{id}")
    public VoitureDto updateVoiture(@PathVariable Long id,
                                    @RequestBody @Valid VoitureDto dto) {
        return voitureService.updateVoiture(id, dto);
    }

    @DeleteMapping("/{id}")
    public String deleteVoiture(@PathVariable Long id) {
        voitureService.deleteVoiture(id);
        return "Voiture deleted successfully";
    }
}
