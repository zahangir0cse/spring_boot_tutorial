package com.springboottutorial.spring_boot_tutorial.service.impl;

import com.springboottutorial.spring_boot_tutorial.dto.UserPrinciple;
import com.springboottutorial.spring_boot_tutorial.model.User;
import com.springboottutorial.spring_boot_tutorial.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {
    private final UserService userService;
    public CustomUserDetailsService(UserService userService){
        this.userService = userService;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.get(username);
        UserPrinciple userPrinciple = UserPrinciple.create(user);
        if(userPrinciple == null){
            throw new UsernameNotFoundException("Username not found");
        }
        return userPrinciple;
    }
}
