package com.shm.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.shm.dto.AddUser;
import com.shm.mapper.UserMapper;
import com.shm.model.User;
import com.shm.repository.UserRepository;
import com.shm.utils.JwtUtil;

@Service
public class AuthService {
  private final UserRepository userRepository;
  private final JwtUtil jwtUtil;
  private final PasswordEncoder passwordEncoder;
  private final UserMapper userMapper;

  AuthService(UserRepository userRepository, JwtUtil jwtUtil, PasswordEncoder passwordEncoder, UserMapper userMapper) {
    this.jwtUtil = jwtUtil;
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
    this.userMapper = userMapper;
  }

  public String login(String email, String password) {
    User user = userRepository.findByEmail(email);
    if (user != null && passwordEncoder.matches(password, user.getPassword())) {
      return jwtUtil.generateToken(email, "ADMIN");
    }
    return null;
  }

  public boolean register(String email, String password) {
    if (userRepository.findByEmail(email) != null) {
      return false;
    }
    User newUser = new User(email, passwordEncoder.encode(password));
    return userRepository.save(newUser) != null;
  }

  public boolean addUser(AddUser addUser) {
    if (userRepository.findByEmail(addUser.getEmail()) != null) {
      return false;
    }
    addUser.setPassword(passwordEncoder.encode(addUser.getPassword()));
    User newUser = userMapper.getUserFromAddUser(addUser);
    return userRepository.save(newUser) != null;
  }

  public boolean check(String token) {
    return jwtUtil.validateToken(token);
  }

  public void extract(String token) {
    jwtUtil.extractEmail(token);
  }
}
