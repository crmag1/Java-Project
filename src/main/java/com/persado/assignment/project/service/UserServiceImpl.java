package com.persado.assignment.project.service;

import com.persado.assignment.project.repository.LoanRepository;
import com.persado.assignment.project.repository.UserRepository;
import com.persado.assignment.project.model.Loan;
import com.persado.assignment.project.model.User;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private LoanRepository loanRepository;

  @Override
  public List<User> findAll() {
    return userRepository.findAll();
  }

  @Override
  public void save(User user) {
    userRepository.save(user);
  }

  @Override
  public String delete(Long id) {
    StringBuilder errorMessage = new StringBuilder();
    // Get the Loans for the User with the given id.
    List<Loan> loans = loanRepository.findByUserIdAndReturnDateIsNull(id);
    // If the User has books on loan, create a String with the name of the books.
    for(int i = 0; i < loans.size(); i++) {
      if(StringUtils.isEmpty(errorMessage.toString())) {
        errorMessage.append(loans.get(i).getBook().getName());
      }
      else{
        errorMessage.append(", " + loans.get(i).getBook().getName());
      }
    }

    // If the User does not have any books on loan, perform the delete action.
    if(loans.size() == 0) {
      userRepository.deleteById(id);
    }
    return errorMessage.toString();
  }
}
