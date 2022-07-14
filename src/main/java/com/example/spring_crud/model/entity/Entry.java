package com.example.spring_crud.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Класс описывает сущьность Entry, её поля.
 * Аннотация объекта @Entity, объект представляет собой таблицу, хранящуюся в базе данных.
 * Каждый экземпляр объекта представляет собой строку в таблице.
 * Благодаря аннотациям из библиотеки lombok @AllArgsConstructor и @NoArgsConstructor не нужно
 * прописывать конструкторы.
 * @Data — это удобная сокращённая аннотация, которая содержит в себе возможности из @ToString,
 * @EqualsAndHashCode, @Getter / @Setter и @RequiredArgsConstructor. Другими словами,
 * @Data генерирует весь бойлерплейт код, который обычно связан с обычными POJO (Plain Old Java Objects).
 *
 * @author Roman Manko
 * @version 1.0
 */

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
