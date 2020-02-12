package com.persado.assignment.project.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class User implements Serializable {

  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String firstName;

  @Column(nullable = false)
  private String lastName;
  private String address;

  @OneToMany(mappedBy="user", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
  @JsonManagedReference
  private List<Loan> loans;

  public User() {
    // no-args constructor
  }

  public User(Long id, String firstName, String lastName, String address) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.address = address;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public List<Loan> getLoans() {
    return loans;
  }

  public void setLoans(List<Loan> loans) {
    this.loans = loans;
  }
}
