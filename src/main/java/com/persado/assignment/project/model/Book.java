package com.persado.assignment.project.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

@Entity
public class Book implements Serializable {

  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Long id;

  @NotNull(message="Name should not be null.")
  @Size(min=2, max=100, message="Last Name should at least 2 characters long.")
  @Column(nullable = false)
  private String name;

  @Length(min = 0, max = 255, message="Summary should not be more than 255 characters.")
  private String summary;

  @NotNull
  @Pattern(regexp = "[\\s]*[0-9]*[1-9]+", message="Only numbers are allowed.")
  @Size(min=10, max=10, message="ISBN should be exactly 10 numbers.")
  @Column(nullable = false)
  private String isbn;

  @NotNull(message="Copies Purchased should not be null.")
  @Range(min = 1, message="Copies Purchased should be at least 1.")
  @Column(nullable = false)
  private int copiesPurchased;
  private int copiesAvailable;

  @Transient
  List<User> availableUsers;

  @OneToMany(mappedBy="book", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
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
    List<Loan> validLoans = new ArrayList<>();
    for(int i = 0; i < loans.size(); i++) {
      if(loans.get(i).getReturnDate() == null) {
        validLoans.add(loans.get(i));
      }
    }
    return validLoans;
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
