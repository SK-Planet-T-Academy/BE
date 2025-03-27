package com.asac7_hackathon.hackathon.domain.users.service;

import com.asac7_hackathon.hackathon.domain.users.controller.dto.UserLoginRequestDto;
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
    if (retrieved.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "등록된 사용자가 없습니다.");
    }
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

  @Transactional(readOnly = true)
  public UserResponseDto login(UserLoginRequestDto request) {
    // 1. 이메일로 사용자 조회
    User user = userRepository.findByUserEmail(request.getEmail())
        .orElseThrow(() -> {
          log.error("이메일이 존재하지 않습니다. email: {}", request.getEmail());
          return new ResponseStatusException(HttpStatus.UNAUTHORIZED, "이메일 또는 비밀번호가 올바르지 않습니다.");
        });

    // 2. 비밀번호 검증 (암호화 X, 평문 비교)
    if (!user.getPassword().equals(request.getPassword())) {
      log.error("비밀번호 불일치. email: {}", request.getEmail());
      throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "이메일 또는 비밀번호가 올바르지 않습니다.");
    }

    // 3. 로그인 성공 시 사용자 정보 반환
    log.info("로그인 성공. email: {}", request.getEmail());
    return UserResponseDto.from(user);
  }

}
