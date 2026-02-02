package com.mytests.spring.pureAnnotationBasedMVC.test.service;

import com.mytests.spring.pureAnnotationBasedMVC.test.model.User;
import com.mytests.spring.pureAnnotationBasedMVC.test.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }


    public void populateDB() {
        System.out.println("====== DB population  =====");
        repository.save(new User("ivan", "ivanov", 20));
        repository.save(new User("petr", "petrov", 30));
        repository.save(new User("sidor", "sidorov", 40));
        System.out.println("===================");
    }

    public List<User> findAll() {
        Iterable<User> users = repository.findAll();
        return (List<User>) users;
    }

    public User save(User user) {
        user = repository.save(user);
        return user;
    }

    public User findById(int id) {
        User user = repository.findById(id).orElse(null);
        return user;
    }

    public void deleteAll() {
        repository.deleteAll();
    }
}