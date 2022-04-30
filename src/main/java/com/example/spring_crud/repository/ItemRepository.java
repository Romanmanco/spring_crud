package com.example.spring_crud.repository;

import com.example.spring_crud.model.entity.Item;
import com.example.spring_crud.model.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

//    @Query
//    Item findAllByTag(String tag);

//     Получить список записей по тэгу постанично
//    придумать как вывести на экран

    @Query
    Item findAllByTagList(List<Tag> tagList);


    //todo использовать Hql/jbql
}
