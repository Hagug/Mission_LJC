package com.example.myboard.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
//@Table(name = "article")
public class Article {
    @Id  // PK
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTOINCREMENT
    private Long id;
    private String topic;
    private String title;
    private String content;
    private String password;

    @OneToMany(mappedBy = "article")
    private List<Comment> comments;
}
