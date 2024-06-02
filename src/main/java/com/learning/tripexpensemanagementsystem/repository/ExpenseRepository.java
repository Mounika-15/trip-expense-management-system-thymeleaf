package com.learning.tripexpensemanagementsystem.repository;

import com.learning.tripexpensemanagementsystem.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
}
