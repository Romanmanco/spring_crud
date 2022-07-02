package com.example.spring_crud.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "entry")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Entry {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "heading", nullable = false)
    private String heading;

    @Column(name = "body", nullable = false)
    private String body;

    @Column(name = "time_create",  nullable = false, updatable = false)
    private LocalDateTime timeCreate;

    @Column(name = "time_update")
    private LocalDateTime timeUpdate;

    @ManyToOne (cascade = CascadeType.ALL)
    @JoinColumn(name = "user_name")
    private User user;

    @ManyToMany (cascade = CascadeType.ALL)
    @JoinColumn(name = "tag")
    private List<Tag> tagList;
}
