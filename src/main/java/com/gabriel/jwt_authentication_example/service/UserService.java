package com.gabriel.jwt_authentication_example.service;

import com.gabriel.jwt_authentication_example.dto.AuthenticationRequestDTO;
import com.gabriel.jwt_authentication_example.enums.UserRole;
import com.gabriel.jwt_authentication_example.model.User;
import com.gabriel.jwt_authentication_example.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return this.userRepository
        .findByEmail(username)
        .orElseThrow(
            () -> new UsernameNotFoundException("No user were found with the provided email"));
  }

  public UserDetails createUser(AuthenticationRequestDTO userData) {

    if (this.userRepository.existsByEmail(userData.email())) {
      throw new IllegalArgumentException("The provided email is already registered");
    }

    User user = new User();
    BeanUtils.copyProperties(userData, user);
    user.setPassword(this.passwordEncoder.encode(userData.password()));
    user.setRole(UserRole.ROLE_STANDARD_USER);

    return this.userRepository.save(user);
  }
}
