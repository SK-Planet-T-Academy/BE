package com.asac7_hackathon.hackathon.user.service;

import com.asac7_hackathon.hackathon.user.controller.dto.UserRequestDto;
import com.asac7_hackathon.hackathon.user.controller.dto.UserResponseDto;
import com.asac7_hackathon.hackathon.user.repository.UserRepository;
import com.asac7_hackathon.hackathon.user.repository.entity.User;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
  private final UserRepository userRepository;

  @Transactional(readOnly = true)
  public List<UserResponseDto> findAll() {
    List<User> retrieved = userRepository.findAll();
    return retrieved.stream().map(UserResponseDto::of).toList();
  }

  @Transactional
  public UserResponseDto insert(UserRequestDto request) {
    User toSave = request.toEntity();
    User saved = userRepository.save(toSave);
    return UserResponseDto.of(saved);
  }
}
