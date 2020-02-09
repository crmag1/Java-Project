package com.persado.assignment.project.dao;

import com.persado.assignment.project.model.Loan;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepository extends JpaRepository<Loan, Long> {

  /**
   * Find active loans by the given user id.
   *
   * @param Id User id
   * @return List of loans
   */
  List<Loan> findByUserIdAndReturnDateIsNull(Long Id);

  /**
   * Find active loans by the given user id and book id.
   *
   * @param userId User id
   * @param bookId Book id
   * @return List of loans
   */
  List<Loan> findByUserIdAndBookIdAndReturnDateIsNull(Long userId, Long bookId);
}
