package com.persado.assignment.project.controller;

import com.persado.assignment.project.model.User;
import com.persado.assignment.project.service.UserService;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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

  /**
   * Delete the User with the given id.
   * Also return error messages, if any.
   *
   * @param id User id
   * @return ModelAndView
   */
  @GetMapping("/delete")
  public ModelAndView delete(@RequestParam("userId") Long id) {
    ModelAndView model = new ModelAndView();
    
    String errors = userService.delete(id);
    if(!StringUtils.isEmpty(errors)) {
      model.addObject("userHasLoansError", "Can't be deleted. "
        + "The User has currently " + errors + " on loan. ");
    }

    // Get all the Users
    List<User> users = userService.findAll();
    // Add the users object to the Model
    model.addObject("users", users);

    model.setViewName("manageUsersForm");
    return model;
  }
}
