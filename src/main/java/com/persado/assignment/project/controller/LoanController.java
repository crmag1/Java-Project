package com.persado.assignment.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/loan")
public class LoanController {

  @GetMapping("/loanBookForm")
  public String showLoanBookForm() {
    return "loanBookForm";
  }

  @GetMapping("/returnBookForm")
  public String showReturnBookForm() {
    return "returnBookForm";
  }

}
