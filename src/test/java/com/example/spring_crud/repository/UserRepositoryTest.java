package com.example.spring_crud.repository;

import com.example.spring_crud.model.entity.Entry;
import com.example.spring_crud.model.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
public class UserRepositoryTest {

    private static final int EXPECTED_VALUE_ZERO = 0;
    private static final String EXPECTED_USER_LOGIN = "Login";
    private static final String EXPECTED_USER_LOGIN_SECOND = "Login 2";
    private static final Long STORED_ID = 1L;
    private static final String EXPECTED_USER_NICK = "Tasty User";
    private static final String EXPECTED_USER_NICK_SECOND = "Tasty User 2";
    private static final String EXPECTED_USER_PASSWORD = "Password";
    private static final String EXPECTED_USER_PASSWORD_SECOND = "Password 2";
    private static final LocalDateTime EXPECTED_USER_REG_TIME = LocalDateTime.now();
    private static final LocalDateTime EXPECTED_USER_REG_TIME_SECOND = LocalDateTime.now().plusHours(1);
    private static final int INDEX_OF_EXPECTED_USER_ONE = 0;

    private static final String HEADING = "heading";
    private static final String BODY = "body";
    private static final LocalDateTime CREATE_TIME = LocalDateTime.now();

    @Autowired
    UserRepository repository;

    @Autowired
    EntryRepository entryRepository;

    @Test
    public void findUserByIdTest() {
        init();

        User storedUser = repository.getById(STORED_ID);

        assertEquals(EXPECTED_USER_NICK, storedUser.getNickName());
        assertEquals(EXPECTED_USER_LOGIN, storedUser.getLogin());
        assertEquals(EXPECTED_USER_PASSWORD, storedUser.getPassword());
        assertEquals(EXPECTED_USER_REG_TIME, storedUser.getTimeRegistration());
    }

    @Test
    public void userSaveTest() {
        init();

        List<User> userList = repository.findAll();

        assertEquals(EXPECTED_USER_NICK, userList.get(INDEX_OF_EXPECTED_USER_ONE).getNickName());
        assertEquals(EXPECTED_USER_LOGIN, userList.get(INDEX_OF_EXPECTED_USER_ONE).getLogin());
        assertEquals(EXPECTED_USER_PASSWORD, userList.get(INDEX_OF_EXPECTED_USER_ONE).getPassword());
        assertEquals(EXPECTED_USER_REG_TIME, userList.get(INDEX_OF_EXPECTED_USER_ONE).getTimeRegistration());
    }

    @Test
    public void userDeleteTest() {
        initWithEntry();

        repository.deleteAll();

        entryRepository.deleteAll();

        List<User> all = repository.findAll();
        List<Entry> allEntry = entryRepository.findAll();

        assertEquals(EXPECTED_VALUE_ZERO, all.size());
        assertEquals(EXPECTED_VALUE_ZERO, allEntry.size());
    }

    @Test
    public void userUpdateTest() {
        init();
        List<User> userList = repository.findAll();

        User storedUser = userList.get(INDEX_OF_EXPECTED_USER_ONE);
        storedUser.setLogin(EXPECTED_USER_LOGIN_SECOND);
        storedUser.setPassword(EXPECTED_USER_PASSWORD_SECOND);
        storedUser.setNickName(EXPECTED_USER_NICK_SECOND);
        storedUser.setTimeRegistration(EXPECTED_USER_REG_TIME_SECOND);

        repository.save(storedUser);

        List<User> userListTwo = repository.findAll();
        assertEquals(EXPECTED_USER_LOGIN_SECOND, userListTwo.get(INDEX_OF_EXPECTED_USER_ONE).getLogin());
        assertEquals(EXPECTED_USER_PASSWORD_SECOND, userListTwo.get(INDEX_OF_EXPECTED_USER_ONE).getPassword());
        assertEquals(EXPECTED_USER_NICK_SECOND, userListTwo.get(INDEX_OF_EXPECTED_USER_ONE).getNickName());
        assertEquals(EXPECTED_USER_REG_TIME_SECOND, userListTwo.get(INDEX_OF_EXPECTED_USER_ONE).getTimeRegistration());
    }

    private void init() {
        User user = new User();
        user.setLogin(EXPECTED_USER_LOGIN);
        user.setNickName(EXPECTED_USER_NICK);
        user.setPassword(EXPECTED_USER_PASSWORD);
        user.setTimeRegistration(EXPECTED_USER_REG_TIME);
        repository.save(user);

        User user2 = new User();
        user2.setLogin(EXPECTED_USER_LOGIN_SECOND);
        user2.setNickName(EXPECTED_USER_NICK_SECOND);
        user2.setPassword(EXPECTED_USER_PASSWORD_SECOND);
        user2.setTimeRegistration(EXPECTED_USER_REG_TIME_SECOND);
        repository.save(user2);
    }


    private void initWithEntry() {
        User user = new User();
        user.setLogin(EXPECTED_USER_LOGIN);
        user.setNickName(EXPECTED_USER_NICK);
        user.setPassword(EXPECTED_USER_PASSWORD);
        user.setTimeRegistration(EXPECTED_USER_REG_TIME);
        repository.save(user);

        Entry entry = new Entry();
        entry.setHeading(HEADING);
        entry.setBody(BODY);
        entry.setTimeCreate(CREATE_TIME);
        entry.setTimeUpdate(CREATE_TIME);
        entry.setUser(user);

        entryRepository.save(entry);
    }
}
