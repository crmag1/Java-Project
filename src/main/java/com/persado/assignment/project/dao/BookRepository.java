package com.persado.assignment.project.dao;

import com.persado.assignment.project.model.Book;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

  /**
   * Find all books that have available copies larger
   * than the given number.
   *
   * @param numberOfBooks Number of available books
   * @return List of books
   */
  List<Book> findByCopiesAvailableGreaterThan(int numberOfBooks);
}
