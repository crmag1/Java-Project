package com.persado.assignment.project.service;

import com.persado.assignment.project.dao.BookRepository;
import com.persado.assignment.project.model.Book;
import java.util.List;
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
}
