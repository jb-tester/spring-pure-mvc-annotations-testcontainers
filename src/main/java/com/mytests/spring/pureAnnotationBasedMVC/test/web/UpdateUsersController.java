package com.mytests.spring.pureAnnotationBasedMVC.test.web;

import com.mytests.spring.pureAnnotationBasedMVC.test.model.User;
import com.mytests.spring.pureAnnotationBasedMVC.test.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
public class UpdateUsersController {
    private final UserService userService;

    public UpdateUsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/updateUserAge")
    public String showUpdateForm(Model model) {
        List<User> invalidAgeUsers = userService.findByAgeIsLessThanEqual(0);

        model.addAttribute("invalidUsers", invalidAgeUsers);
        return "updateUsers";
    }

    @PostMapping("/updateUserAge")
    public String updateUsers(@RequestParam("age") int age, Model model) {
        model.addAttribute("age", age);
        userService.updateIncorrectAges(age);
        return "updateUsers";
    }
}
