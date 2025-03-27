package com.asac7_hackathon.hackathon.domain.users.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;
import org.springframework.web.server.ResponseStatusException;

@ControllerAdvice // 이 클래스가 프로젝트 내의 모든 컨트롤러에서 발생하는 예외를 처리하겠다고 선언한것.
public class GlobalExceptionHandler {

  // 1. DTO 검증 실패 예외 (400 Bad Request)
  @ExceptionHandler(MethodArgumentNotValidException.class) // 각 유형에 대해 처리하는 메서드 정의(유효성 검사 실패 시 발생하는 예외 처리)
  public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
    Map<String, String> errors = new HashMap<>();
    for (FieldError error : ex.getBindingResult().getFieldErrors()) {
      errors.put(error.getField(), error.getDefaultMessage());
      errors.put("status", ex.getStatusCode().toString());
    }
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
  }

  // 2. 특정 데이터가 존재하지 않는 경우 예외 (404 Not Found)
  @ExceptionHandler(ResponseStatusException.class)
  public ResponseEntity<Map<String, String>> handleNotFoundException(ResponseStatusException ex) {
    Map<String, String> errorResponse = new HashMap<>();
    errorResponse.put("error", ex.getReason());
    errorResponse.put("status", ex.getStatusCode().toString());
    return ResponseEntity.status(ex.getStatusCode()).body(errorResponse);
  }

  // 3. 기타 예외 (500 Internal Server Error)
  @ExceptionHandler(Exception.class)
  public ResponseEntity<Map<String, String>> handleGenericException(Exception ex) {
    Map<String, String> errorResponse = new HashMap<>();
    errorResponse.put("error", "서버 내부 오류가 발생했습니다.");
    errorResponse.put("status", HttpStatus.INTERNAL_SERVER_ERROR.toString());
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
  }

}