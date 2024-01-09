package com.gabriel.jwt_authentication_example.controller;

import com.gabriel.jwt_authentication_example.dto.AuthenticationRequestDTO;
import com.gabriel.jwt_authentication_example.dto.AuthenticationResponseDTO;
import com.gabriel.jwt_authentication_example.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
  private final AuthenticationService authenticationService;

  @PostMapping("/signup")
  public ResponseEntity<AuthenticationResponseDTO> signUp(
      @RequestBody @Valid AuthenticationRequestDTO authRequest) {
    AuthenticationResponseDTO response = this.authenticationService.signUp(authRequest);
    return ResponseEntity.status(HttpStatus.OK).body(response);
  }

  @PostMapping("/login")
  public ResponseEntity<AuthenticationResponseDTO> login(
      @RequestBody @Valid AuthenticationRequestDTO authRequest) {
    AuthenticationResponseDTO response = this.authenticationService.login(authRequest);
    return ResponseEntity.status(HttpStatus.OK).body(response);
  }
}
