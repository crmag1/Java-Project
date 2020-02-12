package com.persado.assignment.project.service;

import com.persado.assignment.project.dto.UserDTO;
import java.util.List;

public interface UserService {

  /**
   * Get All Users.
   *
   * @return A List of Users
   */
  public List<UserDTO> findAll();

  /**
   * Save the given User.
   *
   * @param userDTO UserDTO
   */
  public void save(UserDTO userDTO);

  /**
   * Check that the User with the given id does not have any books on loan
   * and delete the User.
   *
   * @param id User id
   * @return String containing error messages.
   */
  public String delete(Long id);
}
