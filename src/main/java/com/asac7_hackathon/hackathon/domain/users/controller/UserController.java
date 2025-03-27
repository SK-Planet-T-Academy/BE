package com.asac7_hackathon.hackathon.domain.users.controller;

import com.asac7_hackathon.hackathon.domain.users.controller.dto.UserRequestDto;
import com.asac7_hackathon.hackathon.domain.users.controller.dto.UserResponseDto;
import com.asac7_hackathon.hackathon.domain.users.service.UserService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  @ResponseBody
  @RequestMapping(value = "", method = RequestMethod.GET)
  public ResponseEntity<List<UserResponseDto>> retrieveAll() {
    List<UserResponseDto> users = userService.findAll();
    return ResponseEntity.ok(users);
  }

  @ResponseBody
  @RequestMapping(value = "", method = RequestMethod.POST)
  public ResponseEntity<UserResponseDto> create(@RequestBody UserRequestDto request) {
    UserResponseDto createdUser = userService.insert(request);
    return ResponseEntity
        .status(HttpStatus.CREATED)
        .body(createdUser);
  }
}