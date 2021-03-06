package com.persado.assignment.project.controller;

import com.persado.assignment.project.dto.BookDTO;
import com.persado.assignment.project.service.LoanService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/loan")
public class LoanController {

  private LoanService loanService;

  @Autowired
  public LoanController(LoanService loanService) {
    this.loanService = loanService;
  }

  /**
   * Get the books with available copies and then open
   * the loan book form.
   *
   * @param model Model
   * @return The html page to be opened.
   */
  @GetMapping("/loanBookForm")
  public String showLoanBookForm(Model model) {
    // Get all the books that are available
    List<BookDTO> bookDTOs = loanService.findBooksForLoan();
    // Add the books attribute to the model
    model.addAttribute("books", bookDTOs);
    return "loanBookForm";
  }

  /**
   * Get all books that can be returned and then open
   * the return book form.
   *
   * @param model Model
   * @return The html page to be opened.
   */
  @GetMapping("/returnBookForm")
  public String showReturnBookForm(Model model) {
    // Get all the books that are currently on loan
    List<BookDTO> bookDTOs = loanService.findBooksForReturn();
    // Add the books attribute to the model
    model.addAttribute("books", bookDTOs);
    return "returnBookForm";
  }

  /**
   * Create a new loan with the Book and the User with
   * the given ids.
   *
   * @param bookId Book id
   * @param userId User id
   * @return The html page to be redirected.
   */
  @PostMapping("/save")
  public String save(@RequestParam("bookId") Long bookId, @RequestParam("userId") Long userId) {
    loanService.createLoan(bookId, userId);
    return "redirect:/loan/loanBookForm";
  }

  /**
   * Complete a loan with the Book and User with the given ids.
   *
   * @param bookId Book id
   * @param userId User id
   * @return The html page to be redirected.
   */
  @PostMapping("/complete")
  public String completeLoan(@RequestParam("bookId") Long bookId, @RequestParam("userId") Long userId) {
    loanService.completeLoan(bookId, userId);
    return "redirect:/loan/returnBookForm";
  }

}
