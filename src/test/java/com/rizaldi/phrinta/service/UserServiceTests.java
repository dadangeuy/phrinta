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

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTests {
    @Autowired
    private UserService service;

    @Test
    public void testGet_expectPresent() {
        User user = generateDummyUser();
        service.insert(user).block();
        Optional<User> optionalUser = service.get(user.getUsername()).blockOptional();
        Assert.assertTrue(optionalUser.isPresent());
    }

    @Test
    public void testGet_expectNotPresent() {
        String username = RandomStringUtils.randomAlphanumeric(10);
        Optional<User> optionalUser = service.get(username).blockOptional();
        Assert.assertFalse(optionalUser.isPresent());
    }

    @Test
    public void testInsert_expectSuccess() {
        User user = generateDummyUser();
        service.insert(user).block();
    }

    @Test
    public void testInsert_expectDuplicateFail() {
        try {
            User user = generateDummyUser();
            service.insert(user).block();
            service.insert(user).block();
            throw new RuntimeException("Not Duplicate");
        } catch (DuplicateKeyException e) {
            // expected
        }
    }

    @Test
    public void testUpdate_expectSuccess() {
        User user = generateDummyUser();
        service.insert(user).block();
        user.setName("Uvuvwevwevwe Onyetenyevwe Ugwemubwem Osas");
        service.update(user).block();
    }

    private User generateDummyUser() {
        return User.builder()
                .name(RandomStringUtils.randomAlphanumeric(10))
                .password(RandomStringUtils.randomAlphanumeric(10))
                .name(RandomStringUtils.randomAlphabetic(10))
                .build();
    }
}
