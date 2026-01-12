package com.example.Wheel_House.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.util.List;

public class UserDto {

    @NotBlank(message = "userName must not be blank")
    private String UserName;

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    @NotBlank(message = "password must not be blank")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Pattern(
            regexp = "^(?=.*\\d)(?=.*[!@#$%^&*()_+\\-=]).{4,}$",
            message = "Password must be at least 4 characters long, contain at least one digit and one special character"
    )
    private String password;
    private List<String> roles;
}
