package com.inu.hackerton.spring.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String sender;

    // Getter, Setter, Constructor
    public User() {}

    public User(String username,String password) {
        this.username = username;
        this.password = password;
    }

}