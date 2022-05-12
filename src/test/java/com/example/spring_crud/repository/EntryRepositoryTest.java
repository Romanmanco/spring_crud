package com.example.spring_crud.repository;

import com.example.spring_crud.model.entity.Entry;
import com.example.spring_crud.model.entity.Tag;
import com.example.spring_crud.model.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class EntryRepositoryTest {

    private final String EXPECTED_TAG_NAME = "tag";
    private static final LocalDateTime EXPECTED_TAG_CREATE_TIME = LocalDateTime.now();

    private final String HEADING = "heading";
    private final String HEADING_SECOND = "heading 2";
    private final String BODY = "body";
    private final String BODY_SECOND = "body 2";
    private final LocalDateTime CREATE_TIME = LocalDateTime.now();
    private final LocalDateTime CREATE_TIME_SECOND = LocalDateTime.now().plusHours(1);
    private final LocalDateTime UPDATE_TIME = LocalDateTime.now().plusDays(1);
    private final LocalDateTime UPDATE_TIME_SECOND = LocalDateTime.now().plusDays(1).plusHours(1);
    private final int EXPECTED_VALUE_ONE = 1;
    private final int INDEX_OF_EXPECTED_ENTRY_ONE = 0;
    private final Long FIRST_USER_ID = 1L;
    private final Long STORED_ID = 1L;

    private static final String EXPECTED_USER_LOGIN = "Login";
    private static final String EXPECTED_USER_LOGIN_SECOND = "Login 2";
    private final String EXPECTED_USER_NICK = "Tasty User";
    private final String EXPECTED_USER_NICK_SECOND = "Tasty User 2";
    private static final String EXPECTED_USER_PASSWORD = "Password";
    private static final String EXPECTED_USER_PASSWORD_SECOND = "Password 2";
    private static final LocalDateTime EXPECTED_USER_REG_TIME = LocalDateTime.now();
    private static final LocalDateTime EXPECTED_USER_REG_TIME_SECOND = LocalDateTime.now().plusHours(1);

    @Autowired
    EntryRepository repository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    TagRepository tagRepository;

    @Test
    public void itemSaveTest() {
        init();

        List<Entry> entryList = repository.findAll();

        assertEquals(EXPECTED_VALUE_ONE, entryList.size());
        assertEquals(BODY, entryList.get(INDEX_OF_EXPECTED_ENTRY_ONE).getBody());
        assertEquals(HEADING, entryList.get(INDEX_OF_EXPECTED_ENTRY_ONE).getHeading());
        assertEquals(CREATE_TIME, entryList.get(INDEX_OF_EXPECTED_ENTRY_ONE).getTimeCreate());
        assertEquals(CREATE_TIME, entryList.get(INDEX_OF_EXPECTED_ENTRY_ONE).getTimeUpdate());
        assertEquals(EXPECTED_USER_LOGIN, entryList.get(INDEX_OF_EXPECTED_ENTRY_ONE).getUser().getLogin());
        assertEquals(EXPECTED_USER_NICK, entryList.get(INDEX_OF_EXPECTED_ENTRY_ONE).getUser().getNickName());
        assertEquals(EXPECTED_USER_PASSWORD, entryList.get(INDEX_OF_EXPECTED_ENTRY_ONE).getUser().getPassword());
        assertEquals(EXPECTED_USER_REG_TIME, entryList.get(INDEX_OF_EXPECTED_ENTRY_ONE).getUser().getTimeRegistration());
    }

    @Test
    public void findByIdTest() {
        init();
        Entry storedEntry = repository.getById(STORED_ID);
        assertNotNull(storedEntry);

        assertEquals(HEADING, storedEntry.getHeading());
        assertEquals(BODY,storedEntry.getBody());
        assertEquals(CREATE_TIME, storedEntry.getTimeCreate());
        assertEquals(UPDATE_TIME,storedEntry.getTimeUpdate().plusDays(1));
        assertEquals(FIRST_USER_ID,storedEntry.getUser().getId());
    }

    //todo проверить все поля, что они обновляются или не обновляются
    //todo CreateDate - не должно обновляться.
    //todo провести исследование, почему разрешена замена юзера и найти, как ее исправить(запретить замену)
    @Test
    public void entryUpdateTest() {
        init();
        List<Entry> entryList = repository.findAll();

        Entry storedEntry = entryList.get(INDEX_OF_EXPECTED_ENTRY_ONE);
        User storedItemUser = storedEntry.getUser();

        storedEntry.setHeading(HEADING_SECOND);
        storedEntry.setBody(BODY_SECOND);
        storedEntry.setTimeCreate(CREATE_TIME_SECOND);
        storedEntry.setTimeUpdate(UPDATE_TIME_SECOND);
        storedEntry.setUser(storedItemUser);

        repository.save(storedEntry);

        List<Entry> entryListTwo = repository.findAll();
        assertEquals(HEADING_SECOND, entryListTwo.get(INDEX_OF_EXPECTED_ENTRY_ONE).getHeading());

        storedEntry.setHeading(HEADING);
        storedEntry.setBody(BODY);
        storedEntry.setTimeCreate(CREATE_TIME);
        storedEntry.setTimeUpdate(CREATE_TIME);

        repository.save(storedEntry);


        assertEquals(EXPECTED_VALUE_ONE, entryList.size());
        assertEquals(BODY, entryList.get(INDEX_OF_EXPECTED_ENTRY_ONE).getBody());
        assertEquals(HEADING, entryList.get(INDEX_OF_EXPECTED_ENTRY_ONE).getHeading());
        assertEquals(CREATE_TIME, entryList.get(INDEX_OF_EXPECTED_ENTRY_ONE).getTimeCreate());
        assertEquals(CREATE_TIME, entryList.get(INDEX_OF_EXPECTED_ENTRY_ONE).getTimeUpdate());
        assertEquals(EXPECTED_USER_NICK, entryList.get(INDEX_OF_EXPECTED_ENTRY_ONE).getUser().getNickName());
    }

    @Test
    public void findPageById(){
        init();
        List<Entry> entryList = repository.findAllByUserId(FIRST_USER_ID);
        assertEquals(EXPECTED_VALUE_ONE, entryList.size());
        assertEquals(BODY, entryList.get(INDEX_OF_EXPECTED_ENTRY_ONE).getBody());
        assertEquals(HEADING, entryList.get(INDEX_OF_EXPECTED_ENTRY_ONE).getHeading());
        assertEquals(CREATE_TIME, entryList.get(INDEX_OF_EXPECTED_ENTRY_ONE).getTimeCreate());
        assertEquals(CREATE_TIME, entryList.get(INDEX_OF_EXPECTED_ENTRY_ONE).getTimeUpdate());
        assertEquals(EXPECTED_USER_NICK, entryList.get(INDEX_OF_EXPECTED_ENTRY_ONE).getUser().getNickName());
    }


    //user обновляется потому что мы инициализируем его с возиожностью обновления и работа идет с помощью докера
    private void checkUserUpdate(Entry storedEntry) {
        User secondUser = userRepository.getById(2L);

        storedEntry.setUser(secondUser);

        //todo запретить менять пользователя у тега.
        Exception exception = assertThrows(DataIntegrityViolationException.class, () -> {
            repository.save(storedEntry);
        });
        String expectedMessage = "could not execute statement; SQL [n/a]; constraint [user_id\" of relation \"items];" +
                " nested exception is org.hibernate.exception.ConstraintViolationException: could not execute statement\n";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    private void init() {
        User user = new User();
        user.setLogin(EXPECTED_USER_LOGIN);
        user.setNickName(EXPECTED_USER_NICK);
        user.setPassword(EXPECTED_USER_PASSWORD);
        user.setTimeRegistration(EXPECTED_USER_REG_TIME);
        userRepository.save(user);

        User user2 = new User();
        user2.setLogin(EXPECTED_USER_LOGIN_SECOND);
        user2.setNickName(EXPECTED_USER_NICK_SECOND);
        user2.setPassword(EXPECTED_USER_PASSWORD_SECOND);
        user2.setTimeRegistration(EXPECTED_USER_REG_TIME_SECOND);
        userRepository.save(user2);

        Entry entry = new Entry();
        entry.setHeading(HEADING);
        entry.setBody(BODY);
        entry.setTimeCreate(CREATE_TIME);
        entry.setTimeUpdate(CREATE_TIME);
        entry.setUser(user);
        entry.setTagList(Arrays.asList());
        repository.save(entry);

        //todo проверить, что теги можно прикрепить к записи
        Tag tag =new Tag();
        tag.setName(EXPECTED_TAG_NAME);
        tag.setTimeCreate(EXPECTED_TAG_CREATE_TIME);
        tagRepository.save(tag);
    }
}