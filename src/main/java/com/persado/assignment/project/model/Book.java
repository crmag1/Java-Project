package com.persado.assignment.project.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Book {

  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Long id;
  private String name;
  private String summary;
  private String ISBN;
  private int copiesPurchased;
  private int copiesAvailable;

  public Book() {
    // no-args constructor
  }

  public Book(Long id, String name, String summary, String ISBN, int copiesPurchased, int copiesAvailable) {
    this.id = id;
    this.name = name;
    this.summary = summary;
    this.ISBN = ISBN;
    this.copiesPurchased = copiesPurchased;
    this.copiesAvailable = copiesAvailable;
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

  public String getISBN() {
    return ISBN;
  }

  public void setISBN(String ISBN) {
    this.ISBN = ISBN;
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
}
