package com.learning.tripexpensemanagementsystem.service.impl;

import com.learning.tripexpensemanagementsystem.dto.JwtAuthResponseDto;
import com.learning.tripexpensemanagementsystem.dto.LoginDto;
import com.learning.tripexpensemanagementsystem.dto.RegisterRequestDto;
import com.learning.tripexpensemanagementsystem.dto.UserDto;
import com.learning.tripexpensemanagementsystem.entity.User;
import com.learning.tripexpensemanagementsystem.exception.FoundException;
import com.learning.tripexpensemanagementsystem.exception.InvalidInputException;
import com.learning.tripexpensemanagementsystem.exception.NotFoundException;
import com.learning.tripexpensemanagementsystem.mapper.UserMapper;
import com.learning.tripexpensemanagementsystem.repository.UserRepository;
import com.learning.tripexpensemanagementsystem.security.JwtTokenProvider;
import com.learning.tripexpensemanagementsystem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;

    private final UserMapper userMapper;

    @Override
    public JwtAuthResponseDto login(LoginDto loginDto) {
        var user = userRepository.findByUsername(loginDto.getUsername());
        if (user == null) {
            throw new NotFoundException("USER_NOT_FOUND", "User not found with username: " + loginDto.getUsername());
        }
        if (!passwordEncoder.matches(loginDto.getPassword(), user.getPassword())) {
            throw new InvalidInputException("INCORRECT_PASSWORD", "password is incorrect, Please enter correct password");
        }

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsername(),
                loginDto.getPassword()
        ));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtTokenProvider.generateToken(authentication);

        return JwtAuthResponseDto.builder()
                .userId(user.getId())
                .tokenType("Bearer")
                .accessToken(token)
                .role(user.getRole())
                .build();
    }

    @Override
    public UserDto register(RegisterRequestDto registerRequestDto) {
        var user = userRepository.findByUsername(registerRequestDto.getUsername());
        if (user != null) {
            throw new FoundException("USER_ALREADY_EXISTS", "Username already exists, please create new username");
        }
        if (!registerRequestDto.getPassword().equals(registerRequestDto.getConfirmPassword())) {
            throw new InvalidInputException("PASSWORD_NOT_MATCHED", "Password and Confirm password not matched");
        }

        var savedUser = userRepository.save(User.builder()
                .username(registerRequestDto.getUsername())
                .password(passwordEncoder.encode(registerRequestDto.getPassword()))
                .role(registerRequestDto.getRole())
                .build());
        return userMapper.userToUserDto(savedUser);
    }

    @Override
    public User getById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("USER_NOT_FOUND", "User not found with id: " + id));
    }
}
