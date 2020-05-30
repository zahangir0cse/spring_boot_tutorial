package com.springboottutorial.spring_boot_tutorial.service;

import com.springboottutorial.spring_boot_tutorial.model.User;

import java.util.List;

public interface UserService {
    User save(User user);
    User update(User user);
    User get(Long id);
    String delete(Long id);
    List<User> getAll();
}
