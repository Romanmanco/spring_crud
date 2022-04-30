package com.example.spring_crud.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "items")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "heading", nullable = false)
    private String heading;

    @Column(name = "body", nullable = false)
    private String body;

    @Column(name = "time_create", nullable = false)
    private LocalDateTime timeCreate;

    @Column(name = "time_update", nullable = false)
    private LocalDateTime timeUpdate;

    @ManyToOne
    private User user;

    @ManyToMany
    private List<Tag> tagList;
}
