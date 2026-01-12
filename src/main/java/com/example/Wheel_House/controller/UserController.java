package com.example.Wheel_House.controller;

import com.example.Wheel_House.dto.UserDto;
import com.example.Wheel_House.mapper.UserMapper;
import com.example.Wheel_House.model.User;
import com.example.Wheel_House.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    UserMapper userMapper;
    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable Long id){
        return userService.getUserById(id);
    }
    @PostMapping
    public ResponseEntity<String> saveUser(@RequestBody @Valid UserDto dto) {
        userService.SaveUser(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body("user added");
    }

    @PutMapping("/username/{username}")
    public UserDto updateUserByUsername(@PathVariable String username, @Valid @RequestBody UserDto dto) {

        User user = userService.updateUserByUsername(username, dto);
        return userMapper.toDto(user);
    }
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "user deleted successfully";
    }
    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }
}
