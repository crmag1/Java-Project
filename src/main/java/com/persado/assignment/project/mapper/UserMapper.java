package com.persado.assignment.project.mapper;

import com.persado.assignment.project.dto.UserDTO;
import com.persado.assignment.project.model.User;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class UserMapper {

  public abstract User userDtoToUser(UserDTO userDTO);

  public abstract UserDTO userToUserDto(User user);

  public abstract List<UserDTO> usersToUserDtos(List<User> users);

  public abstract List<User> userDtosToUser(List<UserDTO> userDTOS);
}
