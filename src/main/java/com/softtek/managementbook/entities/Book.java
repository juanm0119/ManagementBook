package com.softtek.managementbook.entities;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "isbn", unique = true, length = 13)
    private String isbn;

    @Column(name = "title", length = 50)
    private String title;

    @Column(name = "genre", length = 20)
    private String genre;

    @Column(name = "synopsis")
    private String synopsis;

    @Column(name = "published_at")
    private Date publishedAt;

    @Column(name = "created_at")
    private Date createdAt;

}