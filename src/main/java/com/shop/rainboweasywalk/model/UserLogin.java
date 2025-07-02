package com.shop.rainboweasywalk.model;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "user_login")
public class UserLogin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String role;

//    public Object getPassword() {
//    }

    // Getters and Setters
}
