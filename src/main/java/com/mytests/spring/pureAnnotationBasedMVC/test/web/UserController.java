package com.mytests.spring.pureAnnotationBasedMVC.test.web;

import com.mytests.spring.pureAnnotationBasedMVC.test.service.UserService;
import com.mytests.spring.pureAnnotationBasedMVC.test.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {


    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
        userService.populateDB();
    }

    @GetMapping
    public String getUsers(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "users";
    }


    @PostMapping
    public String addUser(@RequestParam("first_name") String first_name, @RequestParam("last_name") String last_name, @RequestParam("age") int age) {
        User user = new User(first_name, last_name, age);
        userService.save(user);
        return "redirect:users";
    }
}