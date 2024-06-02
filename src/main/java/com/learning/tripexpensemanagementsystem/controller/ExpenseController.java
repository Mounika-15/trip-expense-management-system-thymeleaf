package com.learning.tripexpensemanagementsystem.controller;

import com.learning.tripexpensemanagementsystem.dto.AddExpenseRequestDto;
import com.learning.tripexpensemanagementsystem.dto.ExpenseResponseDto;
import com.learning.tripexpensemanagementsystem.dto.UpdateExpenseRequestDto;
import com.learning.tripexpensemanagementsystem.entity.Expense;
import com.learning.tripexpensemanagementsystem.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/expenses")
public class ExpenseController {

    private final ExpenseService expenseService;

    @GetMapping
    public ResponseEntity<List<Expense>> getAll() {
        return new ResponseEntity<>(expenseService.getAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Expense> addExpense(@RequestBody AddExpenseRequestDto expenseRequestDto) {
        return new ResponseEntity<>(expenseService.addExpense(expenseRequestDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Expense> updateExpense(@PathVariable Long id, @RequestBody UpdateExpenseRequestDto expenseRequestDto) {
        return new ResponseEntity<>(expenseService.updateExpense(id, expenseRequestDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteExpense(@PathVariable Long id) {
        expenseService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
