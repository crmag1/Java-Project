package com.persado.assignment.project.controller;

import com.persado.assignment.project.dto.UserDTO;
import com.persado.assignment.project.service.UserService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
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

  @Autowired
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
    UserDTO userDTO = new UserDTO();
    // Add the user attribute to the Model
    model.addAttribute("user", userDTO);
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
    List<UserDTO> userDTOs = userService.findAll();
    // Add the users attribute to the Model
    model.addAttribute("users", userDTOs);
    return "manageUsersForm";
  }

  /**
   * Save the given User.
   *
   * @param userDTO The User to be saved.
   * @return The html page to be redirected.
   */
  @PostMapping("/save")
  public String save(@ModelAttribute("user") @Valid UserDTO userDTO, BindingResult bindingResult) {

    if (bindingResult.hasErrors()) {
      return "addUserForm";
    }

    userService.save(userDTO);
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
    List<UserDTO> userDTOs = userService.findAll();
    // Add the users object to the Model
    model.addObject("users", userDTOs);

    model.setViewName("manageUsersForm");
    return model;
  }
}
