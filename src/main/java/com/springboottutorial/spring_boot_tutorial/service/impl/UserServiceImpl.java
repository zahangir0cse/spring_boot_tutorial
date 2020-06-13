package com.springboottutorial.spring_boot_tutorial.service.impl;

import com.springboottutorial.spring_boot_tutorial.model.User;
import com.springboottutorial.spring_boot_tutorial.repository.UserRepository;
import com.springboottutorial.spring_boot_tutorial.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("userService")
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User update(User user) {
        return userRepository.save(user);
    }

    @Override
    public User get(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if(userOptional.isPresent() && userOptional.get()!= null){
            return userOptional.get();
        }
        return null;
    }

    @Override
    public User get(String username) {
        return userRepository.findByUsernameAndIsActiveTrue(username);
    }

    @Override
    public String delete(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if(userOptional.isPresent() && userOptional.get()!= null){
            User user = userOptional.get();
            userRepository.delete(user);
            return "deletedSuccessfully";
        }
        return "recordNotFound";
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }
}
