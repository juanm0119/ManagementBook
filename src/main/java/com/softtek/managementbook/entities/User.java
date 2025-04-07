package com.softtek.managementbook.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @OneToMany(mappedBy = "user", orphanRemoval = true)
    private List<Book> books = new ArrayList<>();

    @Column(name = "name", length = 50)
    private String fullName;

    @Column(name = "email", unique = true, length = 50)
    private String email;

    @Column(name = "password")
    private String password;

}