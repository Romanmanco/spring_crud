package com.example.spring_crud.repository;

import com.example.spring_crud.model.entity.Entry;
import com.example.spring_crud.model.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Аннотация @Repository используется для указания того что интерфейс TagRepository импользуется для хранения,
 * извлечения, изменения, удаления объектов.
 * Интерфейс наследуется от JpaRepository, благодаря чему можно использовать его методы в проекте.
 *
 * @author Roman Manko
 * @version 1.0
 */

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {

    @Query("SELECT t FROM Tag t WHERE t.id = ?1")
    List<Entry> findAllByTagId(Long id);
}
