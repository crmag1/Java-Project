package com.persado.assignment.project.service;

import com.persado.assignment.project.model.User;
import java.util.List;

public interface UserService {

  /**
   * Get All Users.
   *
   * @return A List of Users
   */
  public List<User> findAll();

  /**
   * Save the given User.
   *
   * @param user User
   */
  public void save(User user);
}
