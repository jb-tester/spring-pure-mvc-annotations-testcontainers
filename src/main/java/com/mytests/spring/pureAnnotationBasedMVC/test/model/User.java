package com.mytests.spring.pureAnnotationBasedMVC.test.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "foo")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "fname")
    private String first_name;
    @Column(name = "lname")
    private String last_name;
    @Column(name = "age")
    private int age;

    public User(String first_name, String last_name, int age) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.age = age;
    }

    public User() {
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;
        return age == user.age && id.equals(user.id) && Objects.equals(first_name, user.first_name) && Objects.equals(last_name, user.last_name);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + Objects.hashCode(first_name);
        result = 31 * result + Objects.hashCode(last_name);
        result = 31 * result + age;
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
               "id=" + id +
               ", first_name='" + first_name + '\'' +
               ", last_name='" + last_name + '\'' +
               ", age=" + age +
               '}';
    }
}
