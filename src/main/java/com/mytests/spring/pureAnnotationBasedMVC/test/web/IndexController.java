package com.mytests.spring.pureAnnotationBasedMVC.test.web;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/")
public class IndexController {

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("title", "Test Application");
        return "index";
    }
}
