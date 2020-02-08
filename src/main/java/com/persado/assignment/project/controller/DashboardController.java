package com.persado.assignment.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DashboardController {

  /**
   * Return the dashboard html.
   *
   * @return The dashboard html.
   */
  @RequestMapping("/dashboard")
  public String showDashboard() {
    return "dashboard";
  }

}
