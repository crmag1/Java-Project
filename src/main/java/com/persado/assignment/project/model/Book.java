package com.persado.assignment.project.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

@Entity
public class Book implements Serializable {

  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Long id;
  private String name;
  private String summary;
  private String isbn;
  private int copiesPurchased;
  private int copiesAvailable;

  @Transient
  List<User> availableUsers;

  @OneToMany(mappedBy="book")
  @JsonManagedReference
  private List<Loan> loans;

  public Book() {
    // no-args constructor
  }

  public Book(Long id, String name, String summary, String isbn, int copiesPurchased, int copiesAvailable, List<Loan> loans) {
    this.id = id;
    this.name = name;
    this.summary = summary;
    this.isbn = isbn;
    this.copiesPurchased = copiesPurchased;
    this.copiesAvailable = copiesAvailable;
    this.loans = loans;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getSummary() {
    return summary;
  }

  public void setSummary(String summary) {
    this.summary = summary;
  }

  public String getIsbn() {
    return isbn;
  }

  public void setIsbn(String isbn) {
    this.isbn = isbn;
  }

  public int getCopiesPurchased() {
    return copiesPurchased;
  }

  public void setCopiesPurchased(int copiesPurchased) {
    this.copiesPurchased = copiesPurchased;
  }

  public int getCopiesAvailable() {
    return copiesAvailable;
  }

  public void setCopiesAvailable(int copiesAvailable) {
    this.copiesAvailable = copiesAvailable;
  }

  public List<Loan> getLoans() {
    return loans;
  }

  public void setLoans(List<Loan> loans) {
    this.loans = loans;
  }

  public List<User> getAvailableUsers() {
    return availableUsers;
  }

  public void setAvailableUsers(List<User> availableUsers) {
    this.availableUsers = availableUsers;
  }
}
