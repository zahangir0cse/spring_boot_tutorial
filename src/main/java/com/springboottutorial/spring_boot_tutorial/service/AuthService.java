package com.springboottutorial.spring_boot_tutorial.service;

import com.springboottutorial.spring_boot_tutorial.dto.LoginDto;
import com.springboottutorial.spring_boot_tutorial.dto.Response;

public interface AuthService {
    Response login(LoginDto loginDto);
}
