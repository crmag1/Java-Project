package com.persado.assignment.project.service;

import com.persado.assignment.project.repository.BookRepository;
import com.persado.assignment.project.repository.LoanRepository;
import com.persado.assignment.project.repository.UserRepository;
import com.persado.assignment.project.model.Book;
import com.persado.assignment.project.model.Loan;
import com.persado.assignment.project.model.User;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoanServiceImpl implements LoanService {

  @Autowired
  private LoanRepository loanRepository;

  @Autowired
  private BookRepository bookRepository;

  @Autowired
  private UserRepository userRepository;

  @Override
  public List<Book> findBooksForLoan() {
    // Get all the books with available copies
    List<Book> books = bookRepository.findByCopiesAvailableGreaterThan(0);
    // Get all the users that are available for loan
    List<User> usersAvailableForLoan = userRepository.getUsersAvailableForLoan();

    // For each book get its users
    for (int i = 0; i < books.size(); i++) {
      List<User> users = userRepository.getUsersAvailableForGivenBook(books.get(i).getId());
      users.retainAll(usersAvailableForLoan);
      books.get(i).setAvailableUsers(users);
    }

    return books;
  }

  @Override
  public List<Book> findBooksForReturn() {
    // Get all books that are currently on loan
    List<Book> books = bookRepository.getAllBooksThatAreOnLoan();

    // For each book get its users
    for (int i = 0; i < books.size(); i++) {
      List<User> users = userRepository.getUsersLoanedBook(books.get(i).getId());
      books.get(i).setAvailableUsers(users);
    }

    return books;
  }

  @Override
  public void createLoan(Long bookId, Long userId) {
    // Get the book with the given id
    Book book = bookRepository.getOne(bookId);

    // Get the user with the given id
    User user = userRepository.getOne(userId);

    // Check the validity of the user and the book
    checkBookAndUserValidity(user, book, bookId, userId);

    // Adjust the amount of the available copies
    book.setCopiesAvailable(book.getCopiesAvailable() - 1);

    // Create a new Loan with the book and user
    Loan loan = new Loan(LocalDate.now(), user, book);

    // Save the new Loan
    loanRepository.save(loan);
  }

  /**
   * Check that the book with the given id exists.
   * Check that the user with the given id exists, does not have more than 2 active loans.
   * Check that no active loan exists with the user and book with the given ids.
   *
   * @param user The User
   * @param book The Book
   * @param bookId Book id
   * @param userId User id
   */
  private void checkBookAndUserValidity(User user, Book book, Long bookId, Long userId) {
    if(book == null) {
      throw new RuntimeException("Did not find Book with id: " + bookId + ".");
    }

    if(book.getCopiesAvailable() <= 0) {
      throw new RuntimeException("There are no copies left for the book with id: " + bookId + ".");
    }

    if(user == null) {
      throw new RuntimeException("Did not find User with id: " + userId + ".");
    }

    // Get number of loans for the User with the given id
    int numberOfLoans = userRepository.getNumberOfLoans(user.getId());
    if(numberOfLoans > 2) {
      throw new RuntimeException("User with id : " + userId + " already has 3 books on loan.");
    }

    List<Loan> existingLoans = loanRepository.findByUserIdAndBookIdAndReturnDateIsNull(userId, bookId);
    if(existingLoans.size() > 0) {
      throw new RuntimeException("This loan already exists.");
    }
  }

  @Override
  public void completeLoan(Long bookId, Long userId) {
    // Get the loan with the given book and user id
    List<Loan> loans = loanRepository.findByUserIdAndBookIdAndReturnDateIsNull(userId, bookId);
    // Get the book
    Book book = bookRepository.getOne(bookId);

    // Check the validity of the loan and the book
    checkLoanAndBookValidity(loans, book, bookId);

    // Adjust the number of available copies.
    book.setCopiesAvailable(book.getCopiesAvailable() + 1);
    // Get the loan
    Loan loan = loans.get(0);
    // Set the return date
    loan.setReturnDate(LocalDate.now());
    // Save the loan
    loanRepository.save(loan);
  }

  /**
   * Check that there are no more than one loans and than at least one exist.
   *
   * @param loans List of loans
   */
  private void checkLoanAndBookValidity(List<Loan> loans, Book book, Long bookId) {
    if(book == null) {
      throw new RuntimeException("Did not find Book with id: " + bookId + ".");
    }
    if(loans.size() == 0) {
      throw new RuntimeException("There is no loan with the given book and user ids.");
    }
    if(loans.size() > 1) {
      throw new RuntimeException("There are 2 or more loans with the given book and user ids.");
    }
  }
}
