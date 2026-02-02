package com.mytests.spring.pureAnnotationBasedMVC.test.service;

import com.mytests.spring.pureAnnotationBasedMVC.test.AppConfig;
import com.mytests.spring.pureAnnotationBasedMVC.test.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ContextConfiguration(classes = AppConfig.class)
@ExtendWith(SpringExtension.class)
class UserServiceTest {


    @Autowired
    private UserService userService;

    @BeforeEach
    void setUp() {
        userService.deleteAll();
        userService.populateDB();
    }

    @Test
    void findAllTest() {
        userService.findAll();
        assertEquals(3, userService.findAll().size());
    }

    @Test
    void findByAgeTest() {
        List<User> usersByAge = userService.findByAge(30);
        assertEquals(1, usersByAge.size());
    }

    @Test
    void saveTest() {
        User user = userService.save(new User("John", "Doe", 25));
        assertNotNull(user);
        int id = user.getId();
        User foundUser = userService.findById(id);
        assertEquals(user, foundUser);
    }
}