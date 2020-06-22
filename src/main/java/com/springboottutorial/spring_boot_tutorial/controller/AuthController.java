package com.springboottutorial.spring_boot_tutorial.controller;

import com.springboottutorial.spring_boot_tutorial.annotations.ApiController;
import com.springboottutorial.spring_boot_tutorial.dto.LoginDto;
import com.springboottutorial.spring_boot_tutorial.dto.Response;
import com.springboottutorial.spring_boot_tutorial.service.AuthService;
import com.springboottutorial.spring_boot_tutorial.util.UrlConstraint;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ApiController
@RequestMapping(UrlConstraint.AuthManagement.ROOT)
public class AuthController {

    private final AuthService authService;
    public AuthController(AuthService authService){
        this.authService = authService;
    }

    @GetMapping(UrlConstraint.AuthManagement.LOGIN)
    public Response login(@RequestBody LoginDto loginDto, HttpServletRequest request, HttpServletResponse response){
        return authService.login(loginDto);
    }
}
