package com.asac7_hackathon.hackathon.domain.users.controller.dto;

import com.asac7_hackathon.hackathon.domain.users.repository.entity.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserFindResponseDto {
  private final Integer id;
  private final String email;
  private final String name;

  public static UserFindResponseDto from(User entity) {
    return new UserFindResponseDto(
        entity.getUserId(),
        entity.getUserEmail(),
        entity.getUserName()
    );
  }
}
