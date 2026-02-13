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
        deleteAll();
        System.out.println("====== DB population  =====");
        repository.save(new User("ivan", "ivanov", 20));
        repository.save(new User("petr", "petrov", 30));
        repository.save(new User("vasily", "vasechkin", 0));
        repository.save(new User("pavel", "pavlov", -1));
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

    public List<User> findByAgeGraterThan(int age) {
        List<User> usersByAge = repository.findUsersByAgeGreaterThan(age);
        return usersByAge;
    }
    public void updateIncorrectAges(int age) {
        List<User> invalidAgeUsers = this.findByAgeIsLessThanEqual(0);
        if (!invalidAgeUsers.isEmpty()) {
            repository.updateAgeWhereAgeIsLessOrEquals0(age);
        }
    }

    public List<User> findByAgeIsLessThanEqual(int age) {
        return repository.findByAgeIsLessThanEqual(age);
    }
}