package com.asac7_hackathon.hackathon.domain.users.service;

import com.asac7_hackathon.hackathon.domain.users.controller.dto.UserRequestDto;
import com.asac7_hackathon.hackathon.domain.users.controller.dto.UserResponseDto;
import com.asac7_hackathon.hackathon.domain.users.repository.UserRepository;
import com.asac7_hackathon.hackathon.domain.users.repository.entity.User;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

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
  public UserResponseDto findById(Integer id) {
    User retrievedUser = userRepository.findById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "유저를 찾을수 없습니다 : " + id));
    UserResponseDto result = UserResponseDto.of(retrievedUser);
    return result;
  }

  @Transactional
  public UserResponseDto save(UserRequestDto request) {
    User toSave = request.toEntity();
    User saved = userRepository.save(toSave);
    return UserResponseDto.of(saved);
  }

  @Transactional
  public UserResponseDto update(Integer id, String email, String password, String name) {
    User user = userRepository.findById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "유저를 찾을수 없습니다 : " + id));
    User param = user.updatedFrom(email, password, name);
    User updated = userRepository.save(param);
    return UserResponseDto.of(updated);
  }

  @Transactional
  public void delete(Integer id) {
    User user = userRepository.findById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "유저를 찾을수 없습니다 : " + id));
    userRepository.delete(user);
  }
}
