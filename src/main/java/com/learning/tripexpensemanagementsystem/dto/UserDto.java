package com.learning.tripexpensemanagementsystem.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.learning.tripexpensemanagementsystem.entity.RoleType;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    private Long id;

    private String username;

    @JsonIgnore
    private String password;

    private RoleType role;
}
