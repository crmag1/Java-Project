package com.persado.assignment.project.service;

import com.persado.assignment.project.dto.BookDTO;
import java.util.List;

public interface BookService {

  /**
   * Get All Books.
   *
   * @return A DTO List of Books
   */
  public List<BookDTO> findAll();

  /**
   * Save the given Book.
   *
   * @param bookDTO BookDTO
   */
  public void save(BookDTO bookDTO);

  /**
   * Get the Book with the given id, if is it currently
   * not on loan delete it.
   *
   * @param id Book id
   */
  public void delete(Long id);
}
