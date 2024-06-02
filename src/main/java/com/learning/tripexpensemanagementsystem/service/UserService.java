package com.learning.tripexpensemanagementsystem.service;

import com.learning.tripexpensemanagementsystem.dto.JwtAuthResponseDto;
import com.learning.tripexpensemanagementsystem.dto.LoginDto;
import com.learning.tripexpensemanagementsystem.dto.RegisterRequestDto;
import com.learning.tripexpensemanagementsystem.dto.UserDto;

public interface UserService {
    JwtAuthResponseDto login(LoginDto loginDto);

    UserDto register(RegisterRequestDto registerRequestDto);
}
