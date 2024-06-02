package com.learning.tripexpensemanagementsystem.controller;

import com.learning.tripexpensemanagementsystem.dto.JwtAuthResponseDto;
import com.learning.tripexpensemanagementsystem.dto.LoginDto;
import com.learning.tripexpensemanagementsystem.dto.RegisterRequestDto;
import com.learning.tripexpensemanagementsystem.dto.UserDto;
import com.learning.tripexpensemanagementsystem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponseDto> login(@RequestBody LoginDto loginDto) {
        return new ResponseEntity<>(userService.login(loginDto), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody RegisterRequestDto registerRequestDto) {
        return new ResponseEntity<>(userService.register(registerRequestDto), HttpStatus.OK);
    }
}
