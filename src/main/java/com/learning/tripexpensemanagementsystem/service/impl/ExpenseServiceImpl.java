package com.learning.tripexpensemanagementsystem.service.impl;

import com.learning.tripexpensemanagementsystem.dto.AddExpenseRequestDto;
import com.learning.tripexpensemanagementsystem.dto.UpdateExpenseRequestDto;
import com.learning.tripexpensemanagementsystem.entity.Expense;
import com.learning.tripexpensemanagementsystem.exception.FoundException;
import com.learning.tripexpensemanagementsystem.exception.NotFoundException;
import com.learning.tripexpensemanagementsystem.repository.ExpenseRepository;
import com.learning.tripexpensemanagementsystem.service.ExpenseService;
import com.learning.tripexpensemanagementsystem.service.TripService;
import com.learning.tripexpensemanagementsystem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExpenseServiceImpl implements ExpenseService {

    private final ExpenseRepository expenseRepository;

    private final UserService userService;

    private final TripService tripService;

    @Override
    public List<Expense> getAll() {
        return expenseRepository.findAll();
    }

    @Override
    public Expense getById(Long id) {
        return expenseRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("EXPENSE_NOT_FOUND", "Expense not found with id: " + id));
    }

    @Override
    public Expense addExpense(AddExpenseRequestDto expenseRequestDto) {
        var user = userService.getById(expenseRequestDto.getUserId());
        var trip = tripService.getById(expenseRequestDto.getTripId());

        return expenseRepository.save(Expense.builder()
                .user(user)
                .trip(trip)
                .description(expenseRequestDto.getDescription())
                .amount(expenseRequestDto.getAmount())
                .build());
    }

    @Override
    public Expense updateExpense(Long id, UpdateExpenseRequestDto expenseRequestDto) {
        var expense = getById(expenseRequestDto.getId());

        expense.setDescription(expenseRequestDto.getDescription());
        expense.setAmount(expenseRequestDto.getAmount());

        return expenseRepository.save(expense);
    }

    @Override
    public void deleteById(Long id) {
        var expense = getById(id);
        var user = userService.getById(expense.getUser().getId());
        var trip = tripService.getById(expense.getTrip().getId());
        user.getExpenses().remove(expense);
        trip.getExpenses().remove(expense);

        expenseRepository.deleteById(id);

        var isExpenseDeleted = expenseRepository.findById(id);
        if (isExpenseDeleted.isPresent()) {
            throw new FoundException("EXPENSE_NOT_DELETED", "Expense not deleted with id: " + id);
        }
    }

}
