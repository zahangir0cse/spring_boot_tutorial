package com.springboottutorial.spring_boot_tutorial.controller;

import com.springboottutorial.spring_boot_tutorial.model.User;
import com.springboottutorial.spring_boot_tutorial.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/create")
    public String createUser(Model model){
        model.addAttribute("user", new User());
        model.addAttribute("result", "");
        model.addAttribute("users", userService.getAll());
        return "adduser";
    }

    @PostMapping("/create")
    public String saveUser(@Valid @ModelAttribute User user, BindingResult result, Model model){
        if(result.hasErrors()){
            return "adduser";
        }
        user = userService.save(user);
        if(user != null){
            model.addAttribute("result", "Record Saved Successfully");
        }else {
            model.addAttribute("result", "Record not Saved Successfully");
        }
        model.addAttribute("users", userService.getAll());
        return "adduser";
    }

//    @GetMapping("/all")
//    public String getAll(Model model){
//        model.addAttribute("users", userService.getAll());
//        return "allUser";
//    }


}
