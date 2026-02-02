package com.mytests.spring.pureAnnotationBasedMVC.test.repositories;

import com.mytests.spring.pureAnnotationBasedMVC.test.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Integer> {

     List<User> findUsersByAgeGreaterThan(int age);
}