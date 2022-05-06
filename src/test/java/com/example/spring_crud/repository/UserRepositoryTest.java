package com.example.spring_crud.repository;

import com.example.spring_crud.model.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
public class UserRepositoryTest {

    private final String NAME = "Name";
    private final String NAME_SECOND = "Name 2";
    private final int EXPECTED_VALUE_ONE = 1;
    private final int INDEX_OF_EXPECTED_USER_ONE = 0;

    @Autowired
    UserRepository repository;

    @Test
    public void findAllUsersTest() {
        init();
        List<User> userList = repository.findAll();
        assertEquals(userList, userList);
    }

    @Test
    public void findUserByIdTest() {
        init();
        List<User> userList = repository.findAll();
        assertNotNull(userList);
    }

    @Test
    public void userSaveTest() {
        init();
        List<User> userList = repository.findAll();
        assertEquals(EXPECTED_VALUE_ONE, userList.size());
        assertEquals(NAME, userList.get(INDEX_OF_EXPECTED_USER_ONE).getName());
    }

    @Test
    public void userUpdateTest() {
        init();
        List<User> userList = repository.findAll();

        User storedUser = userList.get(INDEX_OF_EXPECTED_USER_ONE);
        storedUser.setName(NAME_SECOND);

        repository.save(storedUser);

        List<User> userListTwo = repository.findAll();
        assertEquals(NAME_SECOND, userListTwo.get(INDEX_OF_EXPECTED_USER_ONE).getName());
    }

    private void init() {
        User user = new User();
        user.setName(NAME);
        repository.save(user);
    }
}
