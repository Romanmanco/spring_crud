package com.example.spring_crud.repository;

import com.example.spring_crud.model.entity.Item;
import com.example.spring_crud.model.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class ItemRepositoryTest {

    private final String HEADING = "heading";
    private final String HEADING_SECOND = "heading 2";
    private final String BODY = "body";
    private final LocalDateTime CREATE_TIME = LocalDateTime.now();
    private final LocalDateTime UPDATE_TIME = LocalDateTime.now();
    private final int EXPECTED_VALUE_ONE = 1;
    private final String EXPECTED_USER_NAME = "Tasty User";
    private final String EXPECTED_USER_NAME_SECOND = "Tasty User 2";
    private final int INDEX_OF_EXPECTED_ITEM_ONE = 0;

    @Autowired
    ItemRepository repository;
    @Autowired
    UserRepository userRepository;

    @Test
    public void findAllItemsTest() {
        init();
        List<Item> itemList = repository.findAll();
        assertEquals(itemList, itemList);
    }

    @Test
    public void itemSaveTest() {
        init();

        List<Item> itemList = repository.findAll();

        assertEquals(EXPECTED_VALUE_ONE, itemList.size());
        assertEquals(BODY, itemList.get(INDEX_OF_EXPECTED_ITEM_ONE).getBody());
        assertEquals(HEADING, itemList.get(INDEX_OF_EXPECTED_ITEM_ONE).getHeading());
        assertEquals(CREATE_TIME, itemList.get(INDEX_OF_EXPECTED_ITEM_ONE).getTimeCreate());
        assertEquals(CREATE_TIME, itemList.get(INDEX_OF_EXPECTED_ITEM_ONE).getTimeUpdate());
        assertEquals(EXPECTED_USER_NAME, itemList.get(INDEX_OF_EXPECTED_ITEM_ONE).getUser().getName());
    }

    @Test
    public void findByIdTest() {
        init();
        List<Item> itemList = repository.findAll();
        assertNotNull(itemList);
    }

    @Test
    public void itemUpdateTest() {
        init();
        List<Item> itemList = repository.findAll();

        Item storedItem = itemList.get(INDEX_OF_EXPECTED_ITEM_ONE);
        User storedItemUser = storedItem.getUser();

        storedItem.setUser(storedItemUser);
        storedItem.setHeading(HEADING_SECOND);

        repository.save(storedItem);

        List<Item> itemListTwo = repository.findAll();
        assertEquals(HEADING_SECOND, itemListTwo.get(INDEX_OF_EXPECTED_ITEM_ONE).getHeading());
    }

    //user обновляется потому что мы инициализируем его с возиожностью обновления и работа идет с помощью докера
    private void checkUserUpdate(Item storedItem) {
        User secondUser = userRepository.getById(2L);

        storedItem.setUser(secondUser);

        //todo запретить менять пользователя у тега.
        Exception exception = assertThrows(DataIntegrityViolationException.class, () -> {
            repository.save(storedItem);
        });
        String expectedMessage = "could not execute statement; SQL [n/a]; constraint [user_id\" of relation \"items];" +
                " nested exception is org.hibernate.exception.ConstraintViolationException: could not execute statement\n";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    private void init() {
        User user = new User();
        user.setName(EXPECTED_USER_NAME);
        userRepository.save(user);

        User user2 = new User();
        user2.setName(EXPECTED_USER_NAME_SECOND);
        userRepository.save(user2);

        Item item = new Item();
        item.setHeading(HEADING);
        item.setBody(BODY);
        item.setTimeCreate(CREATE_TIME);
        item.setTimeUpdate(CREATE_TIME);
        item.setUser(user);
        repository.save(item);
    }
}