package com.persado.assignment.project.service;

import com.persado.assignment.project.dto.UserDTO;
import com.persado.assignment.project.mapper.UserMapper;
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

  private UserRepository userRepository;
  private LoanRepository loanRepository;
  private UserMapper userMapper;

  @Autowired
  public UserServiceImpl(
    UserRepository userRepository,
    LoanRepository loanRepository,
    UserMapper userMapper) {
    this.userRepository = userRepository;
    this.loanRepository = loanRepository;
    this.userMapper = userMapper;
  }

  @Override
  public List<UserDTO> findAll() {
    return userMapper.usersToUserDtos(userRepository.findAll());
  }

  @Override
  public void save(UserDTO userDTO) {
    User user = userMapper.userDtoToUser(userDTO);
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
