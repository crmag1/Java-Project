package com.persado.assignment.project.controller;

import com.persado.assignment.project.model.Book;
import com.persado.assignment.project.service.BookService;
import java.util.List;
import javax.validation.Valid;
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
    Book book = new Book();
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
    List<Book> books = bookService.findAll();
    // Add the books attribute to the Model
    model.addAttribute("books", books);
    return "manageBooksForm";
  }

  /**
   * Save the given Book.
   *
   * @param book The Book to be saved.
   * @return The html page to be redirected.
   */
  @PostMapping("/save")
  public String save(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult) {

    if (bindingResult.hasErrors()) {
      return "addBookForm";
    }

    bookService.save(book);
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
