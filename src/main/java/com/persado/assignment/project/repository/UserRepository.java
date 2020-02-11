package com.persado.assignment.project.repository;

import com.persado.assignment.project.model.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {

  /**
   * Find the users that are permitted to loan
   * the book with the given id.
   *
   * @param id Book id
   * @return List of users
   */
  @Query("SELECT DISTINCT u FROM User u WHERE u.id NOT IN "
    + "(SELECT u.id FROM User u LEFT JOIN Loan l ON l.user.id = u.id "
    + "WHERE l.book.id=?1 AND l.returnDate IS NULL)")
  List<User> getUsersAvailableForGivenBook(Long id);

  /**
   * Find users who have currently no more than 3 books on loan.
   *
   * @return List of users
   */
  @Query("SELECT DISTINCT u FROM User u WHERE u.id NOT IN "
    + "(SELECT u.id FROM User u LEFT JOIN Loan l ON l.user.id = u.id "
    + "WHERE l.returnDate IS NULL GROUP BY u.id HAVING COUNT(l.user.id) > 2)")
  List<User> getUsersAvailableForLoan();

  /**
   * Find the amount of active loans for the user with the given id.
   *
   * @param id User id
   * @return Amount of active loans
   */
  @Query("SELECT COUNT(u.id) FROM User u LEFT JOIN Loan l ON l.user.id = u.id "
    + "WHERE l.returnDate IS NULL AND l.user.id=?1")
  int getNumberOfLoans(Long id);

  /**
   * Find the users that have currently on loan the book
   * with the given id.
   *
   * @param id Book id
   * @return List of users
   */
  @Query("SELECT DISTINCT u FROM User u WHERE u.id IN "
    + "(SELECT u.id FROM User u LEFT JOIN Loan l ON l.user.id = u.id "
    + "WHERE l.book.id=?1 AND l.returnDate IS NULL)")
  List<User> getUsersLoanedBook(Long id);
}
