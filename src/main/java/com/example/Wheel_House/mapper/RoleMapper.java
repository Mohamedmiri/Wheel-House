package com.example.Wheel_House.mapper;

import com.example.Wheel_House.dto.RoleDto;
import com.example.Wheel_House.model.Role;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper {

    public RoleDto toDto(Role role){
        if(role==null) {
            return null;
        }else {
            RoleDto dto = new RoleDto();
            dto.setNom(role.getNom());
            return dto;
        }
    }
    public Role toRole(RoleDto dto){
        if(dto==null) {
            return null;
        }else {

            Role r = new Role();

            r.setNom(dto.getNom());
            return r;

        }
    }

}
