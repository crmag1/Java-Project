package com.persado.assignment.project.dto;

import java.util.List;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

public class BookDTO {
  private Long id;

  @NotNull(message="Name should not be null.")
  @Size(min=2, max=100, message="Last Name should at least 2 characters long.")
  private String name;

  @Length(min = 0, max = 255, message="Summary should not be more than 255 characters.")
  private String summary;

  @NotNull
  @Pattern(regexp = "[\\s]*[0-9]*[1-9]+", message="Only numbers are allowed.")
  @Size(min=10, max=10, message="ISBN should be exactly 10 numbers.")
  private String isbn;

  @NotNull(message="Copies Purchased should not be null.")
  @Range(min = 1, message="Copies Purchased should be at least 1.")
  private int copiesPurchased;
  private int copiesAvailable;
  private List<UserDTO> availableUsers;
  private List<String> userLoanNames;

  public BookDTO() {

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

  public List<UserDTO> getAvailableUsers() {
    return availableUsers;
  }

  public void setAvailableUsers(List<UserDTO> availableUsers) {
    this.availableUsers = availableUsers;
  }

  public List<String> getUserLoanNames() {
    return userLoanNames;
  }

  public void setUserLoanNames(List<String> userLoanNames) {
    this.userLoanNames = userLoanNames;
  }
}
