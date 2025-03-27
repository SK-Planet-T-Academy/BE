package com.asac7_hackathon.hackathon.domain.users.controller.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class UserCreateRequestDto {
  @NotBlank
  private String email;
  @NotBlank
  private String password;
  @NotBlank
  private String name;

}
