package com.persado.assignment.project.controller;

import com.persado.assignment.project.dto.BookDTO;
import com.persado.assignment.project.service.BookService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/book")
public class BookController {

  private BookService bookService;

  @Autowired
  public BookController(BookService bookService) {
    this.bookService = bookService;
  }

  /**
   * Create an empty Book and pass it into the Model.
   *
   * @param model Model
   * @return The html page to be opened.
   */
  @GetMapping("/addBookForm")
  public String showAddBookForm(Model model) {
    BookDTO book = new BookDTO();
    model.addAttribute("book", book);
    return "addBookForm";
  }

  /**
   * Get all the Books and pass them into the Model.
   *
   * @param model Model
   * @return The html page to be opened.
   */
  @GetMapping("/manageBooksForm")
  public String showManageBooksForm(Model model) {
    // Get all the Books
    List<BookDTO> bookDTOs = bookService.findAll();
    // Add the books attribute to the Model
    model.addAttribute("books", bookDTOs);
    return "manageBooksForm";
  }

  /**
   * Save the given Book.
   *
   * @param bookDTO The Book to be saved.
   * @return The html page to be redirected.
   */
  @PostMapping("/save")
  public String save(@ModelAttribute("book") @Valid BookDTO bookDTO, BindingResult bindingResult) {

    if (bindingResult.hasErrors()) {
      return "addBookForm";
    }

    bookService.save(bookDTO);
    return "redirect:/dashboard";
  }

  /**
   * Delete the Book with the given id.
   *
   * @param id Book id
   * @return The html page to be opened.
   */
  @GetMapping("/delete")
  public String delete(@RequestParam("bookId") Long id) {
    bookService.delete(id);
    return "redirect:/book/manageBooksForm";
  }

}
