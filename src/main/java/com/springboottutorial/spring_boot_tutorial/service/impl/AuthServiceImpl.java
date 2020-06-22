package com.springboottutorial.spring_boot_tutorial.service.impl;

import com.springboottutorial.spring_boot_tutorial.dto.LoginDto;
import com.springboottutorial.spring_boot_tutorial.dto.LoginResponseDto;
import com.springboottutorial.spring_boot_tutorial.dto.Response;
import com.springboottutorial.spring_boot_tutorial.filter.JwtTokenProvider;
import com.springboottutorial.spring_boot_tutorial.model.User;
import com.springboottutorial.spring_boot_tutorial.repository.UserRepository;
import com.springboottutorial.spring_boot_tutorial.service.AuthService;
import com.springboottutorial.spring_boot_tutorial.util.ResponseBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service("authService")
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    public AuthServiceImpl(JwtTokenProvider jwtTokenProvider, UserRepository userRepository, AuthenticationManager authenticationManager){
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }
    @Override
    public Response login(LoginDto loginDto) {
        User user = userRepository.findByUsernameAndIsActiveTrue(loginDto.getUsername());
        if(user == null){
            return ResponseBuilder.getFailureResponse(HttpStatus.UNAUTHORIZED, "Invalid Username or password");
        }
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
        if(authentication.isAuthenticated()){
            LoginResponseDto loginResponseDto = new LoginResponseDto();
            loginResponseDto.setToken(jwtTokenProvider.generateToken(authentication));
            loginResponseDto.setUsername(user.getUsername());
            return ResponseBuilder.getSuccessResponse(HttpStatus.OK, "Logged In Success", loginResponseDto);
        }
        return ResponseBuilder.getFailureResponse(HttpStatus.BAD_REQUEST, "Invalid Username or password");
    }
}
