package com.persado.assignment.project.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Length;

public class UserDTO {
  private Long id;

  @NotNull(message="First Name should not be null.")
  @Size(min=2, max=100, message="Last Name should at least 2 characters long.")
  private String firstName;

  @NotNull(message="Last Name should not be null.")
  @Size(min=2, max=100, message="Last Name should at least 2 characters long.")
  private String lastName;

  @Length(min = 0, max = 255, message="Address should not be more than 255 characters.")
  private String address;

  public UserDTO() {

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

}
