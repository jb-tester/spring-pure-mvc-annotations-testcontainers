package com.mytests.spring.pureAnnotationBasedMVC.test.repositories;

import com.mytests.spring.pureAnnotationBasedMVC.test.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {


}