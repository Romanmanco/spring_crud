package com.example.spring_crud.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "entries")
//, uniqueConstraints = {
//        @UniqueConstraint(columnNames = "user"),
//        @UniqueConstraint(columnNames = "timeCreate")
//})
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Entry {

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

    @ManyToOne (cascade = CascadeType.ALL)
//    @JoinColumn(name = "user")
    private User user;

    @ManyToMany
//    @JoinColumn(name = "tag")
    private List<Tag> tagList;
}
