package com.persado.assignment.project.service;

import com.persado.assignment.project.model.Book;
import java.util.List;

public interface BookService {

  /**
   * Get All Books.
   *
   * @return A List of Books
   */
  public List<Book> findAll();

  /**
   * Save the given Book.
   *
   * @param book Book
   */
  public void save(Book book);

  /**
   * Get the Book with the given id, if is it currently
   * not on loan delete it.
   *
   * @param id Book id
   */
  public void delete(Long id);
}
