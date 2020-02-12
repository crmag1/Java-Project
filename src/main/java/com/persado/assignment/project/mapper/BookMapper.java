package com.persado.assignment.project.mapper;

import com.persado.assignment.project.dto.BookDTO;
import com.persado.assignment.project.model.Book;
import java.util.ArrayList;
import java.util.List;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public abstract class BookMapper {

  public abstract Book bookDtoToBook(BookDTO bookDTO);

  public abstract BookDTO bookToBookDto(Book book);

  public abstract List<BookDTO> booksToBookDtos(List<Book> books);

  public abstract List<Book> bookDtosToBooks(List<BookDTO> bookDTOS);

  /**
   * Extract the names of the users into to a List and
   * add it to the BookDTO.
   *
   * @param book Book
   * @param bookDTO BookDTO
   */
  @AfterMapping
  public void fillLoanedUserNames(Book book, @MappingTarget BookDTO bookDTO) {
    List<String> userLoanNames = new ArrayList<>();
    for(int i = 0; i < book.getLoans().size(); i++) {
      userLoanNames.add(book.getLoans().get(i).getUser().getFirstName());
    }
    bookDTO.setUserLoanNames(userLoanNames);
  }
}
