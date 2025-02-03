package com.shm.mapper;

import org.springframework.stereotype.Component;

import com.shm.dto.AddUser;
import com.shm.model.User;

@Component
public class UserMapper {
  public User getUserFromAddUser(AddUser addUser) {
    return mapToUserFromAddUser(addUser);
  }

  private User mapToUserFromAddUser(AddUser addUser) {
    return User.builder()
        .name(addUser.getName())
        .email(addUser.getEmail())
        .password(addUser.getPassword())
        .build();
  }
}
