package com.asac7_hackathon.hackathon.domain.users.repository.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", updatable = false)
  private Integer userId;
  @Column(name = "email", updatable = false, unique = true)
  private String userEmail;
  @Column(name = "password", nullable = false)
  private String password;
  @Column(name = "name", nullable = false)
  private String userName;
  @Column(name = "date", nullable = false)
  private LocalDateTime createdAt;

  @Builder
  public User(String userEmail, String password, String userName) {
    this.userEmail = userEmail;
    this.password = password;
    this.userName = userName;
    this.createdAt = LocalDateTime.now();
  }

  public User updatedFrom(String userEmail, String password, String userName) {
    this.userEmail = userEmail;
    this.password = password;
    this.userName = userName;
    return this;
  }
}
