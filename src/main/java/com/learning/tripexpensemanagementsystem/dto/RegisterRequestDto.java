package com.learning.tripexpensemanagementsystem.dto;

import com.learning.tripexpensemanagementsystem.entity.RoleType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterRequestDto {

    private String username;

    private String password;

    private String confirmPassword;

    private RoleType role;
}
