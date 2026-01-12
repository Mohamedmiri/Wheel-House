package com.example.Wheel_House.controller;

import com.example.Wheel_House.dto.ClientDto;
import com.example.Wheel_House.service.ClientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
@CrossOrigin(origins = "http://localhost:4200")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping
    public ClientDto createClient(@RequestBody @Valid ClientDto dto) {

        return clientService.createClient(dto);
    }

    @GetMapping("/{id}")
    public ClientDto getClientById(@PathVariable Long id) {

        return clientService.getClientById(id);
    }

    @GetMapping
    public List<ClientDto> getAllClients() {
        return clientService.getAllClients();
    }

    @PutMapping("/{id}")
    public ClientDto updateClient(@PathVariable Long id,
                                  @RequestBody @Valid ClientDto dto) {
        return clientService.updateClient(id, dto);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public String deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);
        return "Client deleted successfully";
    }
}
