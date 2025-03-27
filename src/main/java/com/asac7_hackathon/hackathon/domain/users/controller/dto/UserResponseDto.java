package com.asac7_hackathon.hackathon.domain.users.controller.dto;

import com.asac7_hackathon.hackathon.domain.users.repository.entity.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserResponseDto {
  private final Integer id;
  private final String email;
  private final String name;
  private final Boolean isLogin;

  public static UserResponseDto from(User entity) {
    return new UserResponseDto(
        entity.getUserId(),
        entity.getUserEmail(),
        entity.getUserName(),
        entity.getIsLogin()
    );
  }
}
