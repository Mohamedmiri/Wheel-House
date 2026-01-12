package com.example.Wheel_House.service;

import com.example.Wheel_House.dto.RoleDto;
import com.example.Wheel_House.mapper.RoleMapper;
import com.example.Wheel_House.model.Role;
import com.example.Wheel_House.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RoleService {

    @Autowired
    RoleRepository roleRepository;
    @Autowired
    RoleMapper roleMapper;

    public RoleDto saveRole (RoleDto roleDto){
        Role role = roleMapper.toRole(roleDto);
        Role saved = roleRepository.save(role);
        return roleMapper.toDto(saved);
    }

    public RoleDto getRoleById(Long id) {
        Role role =roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role not found"));

        return roleMapper.toDto(role);
    }

    public RoleDto updateRole(Long id, RoleDto dto) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role not found"));

        role.setNom(dto.getNom());

        Role saved = roleRepository.save(role);
        return roleMapper.toDto(saved);
    }
    public void deleteRole(Long id) {
        roleRepository.deleteById(id);
    }
    public List<RoleDto> getAllRoles() {
        return roleRepository.findAll().stream()
                .map(roleMapper::toDto)
                .toList();
    }


}
