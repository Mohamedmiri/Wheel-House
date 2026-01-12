package com.example.Wheel_House.service;

import com.example.Wheel_House.dto.ClientDto;
import com.example.Wheel_House.mapper.ClientMapper;
import com.example.Wheel_House.model.Client;
import com.example.Wheel_House.repository.ClientRepository;
import com.example.Wheel_House.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ClientService {
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    ClientMapper clientMapper;

    @Autowired
    OrderRepository orderRepository;

    public ClientDto createClient(ClientDto dto) {
        Client client = clientMapper.toEntity(dto);
        Client saved = clientRepository.save(client);
        return clientMapper.toDto(saved);
    }


    public ClientDto getClientById(Long id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client not found with id: " + id));

        return clientMapper.toDto(client);
    }

    public List<ClientDto> getAllClients() {
        return clientRepository.findAll()
                .stream()
                .map(clientMapper::toDto)
                .toList();
    }


    public ClientDto updateClient(Long id, ClientDto dto) {
        Client existe = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client not found with id: " + id));

        existe.setNomClient(dto.getNomClient());
        existe.setPrenomClient(dto.getPrenomClient());
        existe.setTelephone(dto.getTelephone());
        existe.setCin(dto.getCin());
        existe.setDateNaissance(dto.getDateNaissance());
        existe.setGenre(dto.getGenre());
        existe.setVille(dto.getVille());
        existe.setPaye(dto.getPaye());

        Client saved = clientRepository.save(existe);
        return clientMapper.toDto(saved);
    }

    public void deleteClient(Long id) {
        Client client=clientRepository.findById(id)
                .orElseThrow(()->new RuntimeException("aucun client ce trouve avec ce Id :"+id));
        if(orderRepository.existsByClient(client)){
            throw new RuntimeException(("Impossible de supprimer ce client : il est déjà utilisé dans des orders."));

        }
        clientRepository.delete(client);
    }

}
