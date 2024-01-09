package com.gabriel.jwt_authentication_example.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

  @GetMapping("/all")
  public ResponseEntity<String> allCanSee() {
    return ResponseEntity.ok("All users can see this");
  }

  @GetMapping("/admin")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public ResponseEntity<String> onlyAdmins() {
    return ResponseEntity.ok("Only admins can see this");
  }

  @GetMapping("/user")
  @PreAuthorize("hasRole('ROLE_STANDARD_USER')")
  public ResponseEntity<String> onlyUsers() {
    return ResponseEntity.ok("Only users can see this");
  }
}
