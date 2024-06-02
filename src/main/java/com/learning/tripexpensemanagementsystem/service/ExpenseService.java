package com.learning.tripexpensemanagementsystem.service;

import com.learning.tripexpensemanagementsystem.dto.AddExpenseRequestDto;
import com.learning.tripexpensemanagementsystem.dto.ExpenseResponseDto;
import com.learning.tripexpensemanagementsystem.dto.UpdateExpenseRequestDto;
import com.learning.tripexpensemanagementsystem.entity.Expense;

import java.util.List;

public interface ExpenseService {
    List<Expense> getAll();
    
    Expense getById(Long id);

    Expense addExpense(AddExpenseRequestDto expenseRequestDto);

    Expense updateExpense(Long id, UpdateExpenseRequestDto expenseRequestDto);

    void deleteById(Long id);
}
