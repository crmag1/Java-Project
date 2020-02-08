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

  /**
   * Check that the User with the given id does not have any books on loan
   * and delete the User.
   *
   * @param id User id
   * @return String containing error messages.
   */
  public String delete(Long id);
}
