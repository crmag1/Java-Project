package com.persado.assignment.project.service;

import com.persado.assignment.project.model.Book;
import java.util.List;

public interface LoanService {

  /**
   * Get All Books for Loan.
   *
   * @return A List of Books
   */
  public List<Book> findBooksForLoan();

  /**
   * Get All Books for Return.
   *
   * @return A List of Books
   */
  public List<Book> findBooksForReturn();

  /**
   * Get the Book and User with the given ids, check that the User and the Book are
   * valid. Update the book's available copies. Create a new Loan.
   *
   * @param bookId Book id
   * @param userId User id
   */
  public void createLoan(Long bookId, Long userId);

  /**
   * Get the loan with the given book and user id and set a
   * return date.
   *
   * @param bookId Book id
   * @param userId User id
   */
  public void completeLoan(Long bookId, Long userId);

}
