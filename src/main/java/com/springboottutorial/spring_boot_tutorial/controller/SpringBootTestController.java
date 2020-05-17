package com.springboottutorial.spring_boot_tutorial.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class SpringBootTestController {
    @GetMapping("/")
    public String test(Model model){
        model.addAttribute("appName", "Spring Boot");
        return "test";
    }
}
