package com.example.Wheel_House.controller;

import com.example.Wheel_House.dto.RoleDto;
import com.example.Wheel_House.mapper.RoleMapper;
import com.example.Wheel_House.service.RoleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/role")
public class RoleController {

    @Autowired
    RoleService roleService;
    @Autowired
    RoleMapper roleMapper;
    @GetMapping("/{id}")
    public RoleDto getRoleById(@PathVariable Long id){
        return roleService.getRoleById(id);
    }
    @PostMapping
    public String saveRole(@RequestBody @Valid RoleDto dto){
        roleService.saveRole(dto);
        return "Role added";
    }

    @PutMapping("/{id}")
    public RoleDto updateRole(@PathVariable Long id, @RequestBody @Valid RoleDto dto) {
        return roleService.updateRole(id, dto);
    }
    @DeleteMapping("/{id}")
    public String deleteRole(@PathVariable Long id) {
        roleService.deleteRole(id);
        return "role deleted successfully";
    }
    @GetMapping
    public List<RoleDto> getAllRoles() {
        return roleService.getAllRoles();
    }

}
