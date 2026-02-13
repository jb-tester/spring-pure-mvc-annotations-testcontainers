package com.mytests.spring.pureAnnotationBasedMVC.test.repositories;

import com.mytests.spring.pureAnnotationBasedMVC.test.model.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Integer> {

     List<User> findUsersByAgeGreaterThan(int age);

     List<User> findByAgeIsLessThanEqual(int age);

     @Transactional
     @Modifying
     @Query(value = "update foo set age = ?1 where age <= 0;", nativeQuery = true)
     int updateAgeWhereAgeIsLessOrEquals0(int age);
}