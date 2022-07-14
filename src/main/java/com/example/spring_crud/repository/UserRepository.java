package com.example.spring_crud.repository;

import com.example.spring_crud.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Аннотация @Repository используется для указания того что интерфейс UserRepository импользуется для хранения,
 * извлечения, изменения, удаления объектов.
 * Интерфейс наследуется от JpaRepository, благодаря чему можно использовать его методы в проекте.
 *
 * @author Roman Manko
 * @version 1.0
 */

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
