package com.learning.tripexpensemanagementsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddExpenseRequestDto {
    private Long tripId;

    private Long userId;

    private String description;

    private Double amount;
}
