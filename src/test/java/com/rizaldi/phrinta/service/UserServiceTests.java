package com.rizaldi.phrinta.service;

import com.rizaldi.phrinta.model.User;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTests {
    @Autowired
    private UserService service;

    @Test
    public void testGet_expectPresent() {
        User user = generateDummyUser();
        service.insert(user);
        User dbUser = service.get(user.getUsername());
        Assert.assertNotNull(dbUser);
    }

    @Test
    public void testGet_expectNotPresent() {
        String username = RandomStringUtils.randomAlphanumeric(10);
        User user = service.get(username);
        Assert.assertNull(user);
    }

    @Test
    public void testInsert_expectSuccess() {
        User user = generateDummyUser();
        service.insert(user);
    }

    @Test
    public void testInsert_expectDuplicateFail() {
        try {
            User user = generateDummyUser();
            service.insert(user);
            service.insert(user);
            throw new RuntimeException("Not Duplicate");
        } catch (DuplicateKeyException e) {
            // expected
        }
    }

    @Test
    public void testUpdate_expectSuccess() {
        User user = generateDummyUser();
        service.insert(user);
        user.setName("Uvuvwevwevwe Onyetenyevwe Ugwemubwem Osas");
        service.update(user);
    }

    private User generateDummyUser() {
        return User.builder()
                .name(RandomStringUtils.randomAlphanumeric(10))
                .password(RandomStringUtils.randomAlphanumeric(10))
                .name(RandomStringUtils.randomAlphabetic(10))
                .build();
    }
}
