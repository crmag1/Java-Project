package com.persado.assignment.project.dao;

import com.persado.assignment.project.model.Book;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BookRepository extends JpaRepository<Book, Long> {

  /**
   * Find all books that have available copies larger
   * than the given number.
   *
   * @param numberOfBooks Number of available books
   * @return List of books
   */
  List<Book> findByCopiesAvailableGreaterThan(int numberOfBooks);

  /**
   * Find all the books that are currently on loan.
   *
   * @return List of books
   */
  @Query("SELECT DISTINCT b FROM Book b WHERE b.id IN "
    + "(SELECT b.id FROM Book b LEFT JOIN Loan l ON l.book.id = b.id "
    + "WHERE l.returnDate IS NULL) AND b.copiesPurchased != b.copiesAvailable")
  List<Book> getAllBooksThatAreOnLoan();
}
