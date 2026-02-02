package com.mytests.spring.pureAnnotationBasedMVC.test.web;

import com.mytests.spring.pureAnnotationBasedMVC.test.service.UserService;
import com.mytests.spring.pureAnnotationBasedMVC.test.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {


    private final UserService userService;
    private int age=0;

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

    @GetMapping("/byAge")
    public String getUsersByAge(Model model) {
        List<User> usersByAge = userService.findByAge(age);
        model.addAttribute("usersByAge", usersByAge);
        model.addAttribute("age", age);
        return "byAge";
    }


    @PostMapping(value = "/byAge")
    public String addAge(@RequestParam("age") String age) {
        try {
            this.age = Integer.parseInt(age);
        } catch (final NumberFormatException e) {
            return "redirect:byAge";
        }
        
        return "redirect:byAge";
    }
}