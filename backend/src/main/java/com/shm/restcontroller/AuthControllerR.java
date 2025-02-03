package com.shm.restcontroller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.shm.dto.AddUser;
import com.shm.dto.LoginUser;
import com.shm.service.AuthService;

@RestController
public class AuthControllerR {
  private final AuthService authService;

  AuthControllerR(AuthService authService) {
    this.authService = authService;
  }

  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody LoginUser loginUser) {
    String email = loginUser.getEmail();
    String pass = loginUser.getPassword();
    String token = authService.login(email, pass);
    if (token == null) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid acces");
    }
    return ResponseEntity.ok(new AuthResponse(token));
  }

  @PostMapping("/register")
  public ResponseEntity<?> register() {
    boolean resp = authService.register("test", "test");
    if (resp) {
      return ResponseEntity.ok("User registered successfully");
    }
    return ResponseEntity.status(HttpStatus.CONFLICT).body("Nu s-a putut crea userul");
  }

  @PostMapping("/register-user")
  public ResponseEntity<?> registerUser(@RequestBody AddUser addUser) {
    boolean resp = authService.addUser(addUser);
    if (resp) {
      return ResponseEntity.ok("User registered successfully");
    }
    return ResponseEntity.status(HttpStatus.CONFLICT).body("Nu s-a putut crea userul");
  }

  @PostMapping("/check")
  public ResponseEntity<?> check(@RequestBody Map<String, String> requestBody) {
    String token = requestBody.get("token");
    boolean resp = authService.check(token);
    if (resp) {
      return ResponseEntity.ok("Token valid");
    }
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token invalid");
  }

  static class AuthResponse {
    private String token;

    public AuthResponse(String token) {
      this.token = token;
    }

    public String getToken() {
      return token;
    }
  }

}
