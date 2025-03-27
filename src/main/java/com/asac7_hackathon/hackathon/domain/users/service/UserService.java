package com.asac7_hackathon.hackathon.domain.users.service;

import com.asac7_hackathon.hackathon.domain.users.controller.dto.UserResponseDto;
import com.asac7_hackathon.hackathon.domain.users.controller.dto.UserUpsertRequestDto;
import com.asac7_hackathon.hackathon.domain.users.repository.UserRepository;
import com.asac7_hackathon.hackathon.domain.users.repository.entity.User;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
  private final UserRepository userRepository;

  @Transactional(readOnly = true)
  public List<UserResponseDto> findAll() {
    List<User> retrieved = userRepository.findAll();
    return retrieved.stream().map(UserResponseDto::from).toList();
  }

  @Transactional
  public UserResponseDto findById(Integer id) {
    User retrievedUser = userRepository.findById(id)
        .orElseThrow(() -> {
          log.error("유저를 찾을 수 없습니다. ID : {}", id);
          return new ResponseStatusException(HttpStatus.NOT_FOUND, "유저를 찾을수 없습니다 : " + id);
        });
    UserResponseDto result = UserResponseDto.from(retrievedUser);
    log.info("유저 반환 완료. ID : {}", id);
    return result;
  }

  @Transactional
  public UserResponseDto save(UserUpsertRequestDto request) {
    User toSave = request.toEntity();
    User saved = userRepository.save(toSave);
    log.info("유저 저장 완료. ID : {}", request.getEmail());
    return UserResponseDto.from(saved);
  }

  @Transactional
  public UserResponseDto update(Integer id, String email, String password, String name) {
    User user = userRepository.findById(id)
        .orElseThrow(() -> {
          log.error("업데이트하려는 유저를 찾을 수 없습니다. ID : {}", id);
          return new ResponseStatusException(HttpStatus.NOT_FOUND, "업데이트하려는 유저를 찾을수 없습니다 : " + id);
        });
    User param = user.updatedFrom(email, password, name);
    User updated = userRepository.save(param);
    log.info("유저 업데이트 완료. ID : {}", id);
    return UserResponseDto.from(updated);
  }

  @Transactional
  public void delete(Integer id) {
    User user = userRepository.findById(id)
        .orElseThrow(() -> {
          log.error("삭제하려는 유저를 찾을 수 없습니다. ID : {}", id);
          return new ResponseStatusException(HttpStatus.NOT_FOUND, "삭제하려는 유저를 찾을수 없습니다 : " + id);
        });
    userRepository.delete(user);
    log.info("유저 삭제 완료. ID : {}", id);
  }
}
