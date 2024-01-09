package com.gabriel.jwt_authentication_example.service;

import com.gabriel.jwt_authentication_example.dto.AuthenticationRequestDTO;
import com.gabriel.jwt_authentication_example.dto.AuthenticationResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

  private final UserService userService;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;

  public AuthenticationResponseDTO signUp(AuthenticationRequestDTO authRequest) {
    UserDetails savedUser = this.userService.createUser(authRequest);
    String jwtToken = this.jwtService.generateToken(savedUser);
    return new AuthenticationResponseDTO(jwtToken);
  }

  public AuthenticationResponseDTO login(AuthenticationRequestDTO authRequest) {
    this.authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(authRequest.email(), authRequest.password()));

    UserDetails user = this.userService.loadUserByUsername(authRequest.email());
    String jwtToken = this.jwtService.generateToken(user);
    return new AuthenticationResponseDTO(jwtToken);
  }
}
