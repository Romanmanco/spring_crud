package com.example.spring_crud.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Класс описывает сущьность Tag, её поля.
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
@Table(name = "tag")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "time_create", nullable = false)
    private LocalDateTime timeCreate;

    @ManyToMany (cascade = CascadeType.ALL, mappedBy = "tagList")
    private List<Entry> entry;
}
