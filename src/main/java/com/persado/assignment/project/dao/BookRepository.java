package com.persado.assignment.project.dao;

import com.persado.assignment.project.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

}
