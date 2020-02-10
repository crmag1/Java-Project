package com.persado.assignment.project.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Loan implements Serializable {

  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Long id;
  private LocalDate loanDate;
  private LocalDate returnDate;

  @ManyToOne
  @JoinColumn(name = "user_id", referencedColumnName = "id")
  @JsonBackReference
  private User user;

  @ManyToOne(fetch=FetchType.LAZY)
  @JoinColumn(name = "book_id", referencedColumnName = "id")
  @JsonBackReference
  private Book book;

  public Loan() {
    // no-args constructor
  }

  public Loan(Long id, LocalDate loanDate, LocalDate returnDate, User user, Book book) {
    this.id = id;
    this.loanDate = loanDate;
    this.returnDate = returnDate;
    this.user = user;
    this.book = book;
  }

  public Loan(LocalDate loanDate, User user, Book book) {
    this.loanDate = loanDate;
    this.user = user;
    this.book = book;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public LocalDate getLoanDate() {
    return loanDate;
  }

  public void setLoanDate(LocalDate loanDate) {
    this.loanDate = loanDate;
  }

  public LocalDate getReturnDate() {
    return returnDate;
  }

  public void setReturnDate(LocalDate returnDate) {
    this.returnDate = returnDate;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Book getBook() {
    return book;
  }

  public void setBook(Book book) {
    this.book = book;
  }
}
