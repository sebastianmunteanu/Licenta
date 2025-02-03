package com.shm.utils;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

  @Value("${SECRET_KEY}")
  private String secretKey;

  private SecretKey getSecretKey() {
    byte[] keyBytes = Decoders.BASE64.decode(secretKey);
    return Keys.hmacShaKeyFor(keyBytes);
  }

  public String generateToken(String email, String role) {
    try {
      return Jwts.builder()
          .subject(email)
          .claim("role", role)
          .issuedAt(new Date())
          .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1 oră
          .signWith(getSecretKey())
          .compact();

    } catch (JwtException e) {
      System.err.println("Error generating JWT: " + e.getMessage());
      e.printStackTrace();
      return null;
    }
  }

  public String extractEmail(String token) {
    return Jwts.parser().verifyWith(getSecretKey()).build().parseSignedClaims(token).getPayload().getSubject();
  }

  public boolean validateToken(String token) {
    SecretKey key = getSecretKey();
    JwtParser jwtParser = Jwts.parser().verifyWith(key).build();
    try {
      jwtParser.parse(token);
      System.out.println("token valid");
      return true;
    } catch (JwtException e) {
      System.out.println("token invalid");
      return false;
    }
  }

}
