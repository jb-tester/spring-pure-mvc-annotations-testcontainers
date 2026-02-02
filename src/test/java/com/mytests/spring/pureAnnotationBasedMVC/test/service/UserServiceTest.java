package com.mytests.spring.pureAnnotationBasedMVC.test.service;

import com.mytests.spring.pureAnnotationBasedMVC.test.AppConfig;
import com.mytests.spring.pureAnnotationBasedMVC.test.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ContextConfiguration(classes = AppConfig.class)
@ExtendWith(SpringExtension.class)
class UserServiceTest {


    @Autowired
    private UserService userService;



    @org.junit.jupiter.api.Test
    void findAll() {
        userService.populateDB();
        userService.findAll();
        assertEquals(3, userService.findAll().size());
    }

    @org.junit.jupiter.api.Test
    void save() {
        User user = userService.save(new User("John", "Doe", 25));
        assertNotNull(user);
        int id = user.getId();
        User foundUser = userService.findById(id);
        assertEquals(user, foundUser);
    }
}