package com.persado.assignment.project.dao;

import com.persado.assignment.project.model.Loan;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepository extends JpaRepository<Loan, Long> {
  List<Loan> findByUserId(Long Id);
}
