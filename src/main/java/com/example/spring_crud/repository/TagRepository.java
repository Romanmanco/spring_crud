package com.example.spring_crud.repository;

import com.example.spring_crud.model.entity.Entry;
import com.example.spring_crud.model.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag, Long> {

    @Query("SELECT t FROM Tag t WHERE t.id = ?1")
    List<Entry> findAllByTagId(Long id);
}
