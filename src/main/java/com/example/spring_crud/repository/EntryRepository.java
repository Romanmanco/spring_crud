package com.example.spring_crud.repository;

import com.example.spring_crud.model.entity.Entry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Аннотация @Repository используется для указания того что интерфейс EntryRepository импользуется для хранения,
 * извлечения, изменения, удаления объектов.
 * Интерфейс наследуется от JpaRepository, благодаря чему можно использовать его методы в проекте.
 *
 * @author Roman Manko
 * @version 1.0
 */


@Repository
public interface EntryRepository extends JpaRepository<Entry, Long> {

    @Query("SELECT e FROM Entry e WHERE e.user.id = ?1")
    List<Entry> findAllByUserId(Long id);

//    @Query("SELECT e FROM Entry e WHERE e.user.login = e.login")
//    List<Entry> findUserByName(String name);
//
//    @Query("SELECT e FROM Entry e WHERE e.user.nickName = ?1")
//    List<Entry> findAllUserByNickName(String nickName);

}
