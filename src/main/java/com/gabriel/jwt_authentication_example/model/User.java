package com.gabriel.jwt_authentication_example.model;

import com.gabriel.jwt_authentication_example.enums.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
public class User implements UserDetails {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id_user")
  private UUID id;

  @Column(nullable = false, unique = true)
  private String email;

  @Column(nullable = false)
  private String password;

  @Enumerated(EnumType.STRING)
  private UserRole role;

  private boolean isAccountExpired;
  private boolean isAccountLocked;
  private boolean isCredentialsExpired;
  private boolean isEnabled;

  public User() {
    this.isAccountExpired = false;
    this.isAccountLocked = false;
    this.isCredentialsExpired = false;
    this.isEnabled = true;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of(new SimpleGrantedAuthority(this.role.name()));
  }

  @Override
  public String getUsername() {
    return this.email;
  }

  @Override
  public boolean isAccountNonExpired() {
    return !this.isAccountExpired;
  }

  @Override
  public boolean isAccountNonLocked() {
    return !this.isAccountLocked;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return !this.isCredentialsExpired;
  }

  @Override
  public boolean isEnabled() {
    return this.isEnabled;
  }
}
