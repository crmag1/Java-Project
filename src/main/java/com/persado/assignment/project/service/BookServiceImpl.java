package com.persado.assignment.project.service;

import com.persado.assignment.project.repository.BookRepository;
import com.persado.assignment.project.model.Book;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {

  @Autowired
  private BookRepository bookRepository;

  @Override
  public List<Book> findAll() {
    return bookRepository.findAll();
  }

  @Override
  public void save(Book book) {
    if (book.getId() == null) {
      book.setCopiesAvailable(book.getCopiesPurchased());
    }
    bookRepository.save(book);
  }

  @Override
  public void delete(Long id) {
    // Try to find the book with the given id.
    Optional<Book> bookOptional = bookRepository.findById(id);
    // If a book with the given id exists, check if it is currently on loan.
    if(bookOptional.isPresent()) {
      // Get the book
      Book book = bookOptional.get();
      if(book.getLoans().size() == 0) {
        // Delete the book
        bookRepository.deleteById(id);
      } else {
        // The book is currently on loan
        throw new RuntimeException("The Book with id: " + id + " is currently on loan. Cannot be deleted.");
      }
    } else {
      // The requested book does not exist
      throw new RuntimeException("Did not find Book with id: " + id + ".");
    }
  }
}
