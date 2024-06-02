package com.learning.tripexpensemanagementsystem.dto;

import com.learning.tripexpensemanagementsystem.entity.RoleType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JwtAuthResponseDto {

    private Long userId;

    private String accessToken;

    private String tokenType = "Bearer";

    private RoleType role;
}
