package com.asac7_hackathon.hackathon.domain.users.controller;

import com.asac7_hackathon.hackathon.domain.users.controller.dto.UserFindResponseDto;
import com.asac7_hackathon.hackathon.domain.users.controller.dto.UserLoginRequestDto;
import com.asac7_hackathon.hackathon.domain.users.controller.dto.UserUpsertRequestDto;
import com.asac7_hackathon.hackathon.domain.users.controller.dto.UserResponseDto;
import com.asac7_hackathon.hackathon.domain.users.service.UserService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  @ResponseBody
  @RequestMapping(value = "", method = RequestMethod.GET)
  public ResponseEntity<List<UserFindResponseDto>> retrieveAll() {
    log.info(userService.getClass().toString());
    List<UserFindResponseDto> users = userService.findAll();
    return ResponseEntity
        .status(HttpStatus.OK)
        .body(users);
  }

  @RequestMapping(value = {"/{id}"}, method = RequestMethod.GET)
  public ResponseEntity<UserFindResponseDto> read(@PathVariable Integer id) {
    log.info(userService.getClass().toString());
    UserFindResponseDto user = userService.findById(id);
    return ResponseEntity
        .status(HttpStatus.OK)
        .body(user);
  }

  @ResponseBody
  @RequestMapping(value = "", method = RequestMethod.POST)
  public ResponseEntity<UserResponseDto> save(@RequestBody @Valid UserUpsertRequestDto request) {
    log.info(userService.getClass().toString());
    UserResponseDto createdUser = userService.save(request);
    return ResponseEntity
        .status(HttpStatus.CREATED)
        .body(createdUser);
  }

  @ResponseBody
  @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
  public ResponseEntity<UserResponseDto> update(@PathVariable Integer id, @RequestBody UserUpsertRequestDto request) {
    log.info(userService.getClass().toString());
    UserResponseDto user = userService.update(id, request.getEmail(), request.getPassword(), request.getName());
    return ResponseEntity
        .status(HttpStatus.ACCEPTED)
        .body(user);
  }

  @ResponseBody
  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<Void> delete(@PathVariable Integer id) {
    log.info(userService.getClass().toString());
    userService.delete(id);
    return ResponseEntity
        .status(HttpStatus.ACCEPTED)
        .body(null);
  }

  // 로그인 API (Spring Security 적용 안 됨)
  @ResponseBody
  @RequestMapping(value = "/login", method = RequestMethod.POST)
  public ResponseEntity<UserResponseDto> login(@RequestBody @Valid UserLoginRequestDto request) {
    log.info("로그인 요청: {}", request.getEmail());
    UserResponseDto user = userService.login(request);
    return ResponseEntity.ok(user);
  }

  @ResponseBody
  @RequestMapping(value = "/logout/{id}", method = RequestMethod.POST)
  public ResponseEntity<UserResponseDto> logout(@PathVariable Integer id) {
    UserFindResponseDto user = userService.findById(id);
    String email = user.getEmail();
    log.info("로그아웃 요청: {}", email);
    UserResponseDto logoutEmail = userService.logout(email);

    return ResponseEntity.ok(logoutEmail);
  }

}