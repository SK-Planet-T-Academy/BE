package com.asac7_hackathon.hackathon.domain.users.controller.dto;

import com.asac7_hackathon.hackathon.domain.users.repository.entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserRequestDto {

  private final Integer id;
  @NotBlank(message = "이메일을 입력해주세요.")
  @Email(message = "이메일 형식이 올바르지 않습니다.")
  private final String email;

  @NotBlank(message = "비밀번호를 입력해주세요.")
  private final String password;

  @NotBlank(message = "이름을 입력해주세요.")
  private final String name;

  public User toEntity() {
    return new User(this.email, this.password, this.name);
  }
}
