package com.example.Wheel_House.mapper;

import com.example.Wheel_House.dto.UserDto;
import com.example.Wheel_House.model.Role;
import com.example.Wheel_House.model.User;
import com.example.Wheel_House.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Component
public class UserMapper {

    @Autowired
    RoleRepository roleRepository;

    public User toUser (UserDto dto){
        if (dto == null) return null;

        User user = new User();
        user.setUserName(dto.getUserName());
        user.setPassword(dto.getPassword());

        List<Role> roles = Optional.ofNullable(dto.getRoles())
                .orElse(List.of())
                .stream()
                .map(name -> Optional.ofNullable(roleRepository.findByNom(name))
                        .orElseThrow(() -> new RuntimeException("Role not found: " + name)))
                .toList();

        user.setRoles(roles);
        return user;
    }

    public UserDto toDto (User user){
        if (user == null) return null;

        UserDto dto = new UserDto();
        dto.setUserName(user.getUserName());
        dto.setPassword(user.getPassword());

        dto.setRoles(
                user.getRoles().stream()
                        .map(Role::getNom)
                        .toList()
        );

        return dto;
    }

    public void updateEntity(User user, UserDto dto) {

        user.setUserName(dto.getUserName());
        user.setPassword(dto.getPassword());

        if (dto.getRoles() != null) {
            List<Role> roles = dto.getRoles()
                    .stream()
                    .map(name -> {
                        Role role = roleRepository.findByNom(name);
                        if (role == null) {
                            throw new RuntimeException("Role not found: " + name);
                        }
                        return role;
                    })
                    .collect(Collectors.toCollection(ArrayList::new)); // FIX: mutable

            user.setRoles(roles);
        }
    }


}
