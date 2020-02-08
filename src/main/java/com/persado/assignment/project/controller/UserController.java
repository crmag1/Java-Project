package com.persado.assignment.project.controller;

import com.persado.assignment.project.model.User;
import com.persado.assignment.project.service.UserService;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

  private UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  /**
   * Create an empty User and pass it into the Model.
   *
   * @param model Model
   * @return The html page to be opened.
   */
  @GetMapping("/addUserForm")
  public String showAddUserForm(Model model) {
    // Create a new User in order to pass it into the add user form
    User user = new User();
    // Add the user attribute to the Model
    model.addAttribute("user", user);
    return "addUserForm";
  }

  /**
   * Get all the Users and pass them into the Model.
   *
   * @param model Model
   * @return The html page to be opened.
   */
  @GetMapping("/manageUsersForm")
  public String showManageUsersForm(Model model) {
    // Get all the Users
    List<User> users = userService.findAll();
    // Add the users attribute to the Model
    model.addAttribute("users", users);
    return "manageUsersForm";
  }

  /**
   * Save the given User.
   *
   * @param user The User to be saved.
   * @return The html page to be redirected.
   */
  @PostMapping("/save")
  public String save(@ModelAttribute("user") User user) {
    userService.save(user);
    return "redirect:/dashboard";
  }
}
