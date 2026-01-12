package com.example.Wheel_House.service;

import com.example.Wheel_House.dto.UserDto;
import com.example.Wheel_House.mapper.UserMapper;
import com.example.Wheel_House.model.User;
import com.example.Wheel_House.repository.OrderRepository;
import com.example.Wheel_House.repository.UserRepository;
import jakarta.validation.constraints.NegativeOrZero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserMapper userMapper;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    public void SaveUser(UserDto dto){
        User user = userMapper.toUser(dto);
        if (dto.getPassword() != null && !dto.getPassword().isBlank()) {
            user.setPassword(passwordEncoder.encode(dto.getPassword()));
        }
        userRepository.save(user);
    }

    public UserDto getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return userMapper.toDto(user);
    }
    public List<UserDto> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toDto)
                .toList();
    }
    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("aucun user ce trouve avec ce Id " + id));

        if (orderRepository.existsByUser(user)) {
            throw new RuntimeException("Impossible de supprimer cet utilisateur : il est utilisé dans des orders.");
        }

        userRepository.delete(user);
    }


    public User updateUserByUsername(String username, UserDto dto) {
        User user = userRepository.findByUserName(username)
                .orElseThrow(() -> new RuntimeException("user non trouve"));

        userMapper.updateEntity(user, dto);

        if (dto.getPassword() != null && !dto.getPassword().isBlank()) {
            user.setPassword(passwordEncoder.encode(dto.getPassword()));
        }

        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        var authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getNom()))
                .toList();

        return new org.springframework.security.core.userdetails.User(
                user.getUserName(),
                user.getPassword(),
                authorities
        );
    }


}
