package com.example.Wheel_House.mapper;

import com.example.Wheel_House.dto.ClientDto;
import com.example.Wheel_House.model.Client;
import jdk.jfr.Category;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper {


    public ClientDto toDto(Client client) {
        if (client == null) return null;

        ClientDto dto = new ClientDto();
        dto.setId(client.getId());
        dto.setNomClient(client.getNomClient());
        dto.setPrenomClient(client.getPrenomClient());
        dto.setTelephone(client.getTelephone());
        dto.setCin(client.getCin());
        dto.setDateNaissance(client.getDateNaissance());
        dto.setGenre(client.getGenre());
        dto.setVille(client.getVille());
        dto.setPaye(client.getPaye());

        return dto;
    }

    public Client toEntity(ClientDto dto) {
        if (dto == null) return null;

        Client client = new Client();
        client.setId(dto.getId());
        client.setNomClient(dto.getNomClient());
        client.setPrenomClient(dto.getPrenomClient());
        client.setTelephone(dto.getTelephone());
        client.setCin(dto.getCin());
        client.setDateNaissance(dto.getDateNaissance());
        client.setGenre(dto.getGenre());
        client.setVille(dto.getVille());
        client.setPaye(dto.getPaye());

        return client;
    }


}
