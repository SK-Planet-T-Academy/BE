package com.asac7_hackathon.hackathon.domain.users.controller.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
  private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
  // 1. DTO 검증 실패 예외 (400 Bad Request)
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex, HttpServletRequest request) {
    // 여러 개의 에러 메시지를 모아서 전달
    Map<String, String> errors = new HashMap<>();
    for (FieldError error : ex.getBindingResult().getFieldErrors()) {
      errors.put(error.getField(), error.getDefaultMessage());
    }

    // 첫 번째 에러 메시지만 전달 (여러 개 처리할 경우 errors.toString() 사용 가능)
    String message = errors.values().iterator().next();

    ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST, message, ex);
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
  }

  // 데이터가 존재하지 않을 때 - 404 Not Found
  @ExceptionHandler(ResponseStatusException.class)
  public ResponseEntity<ErrorResponse> handleNotFoundException(ResponseStatusException ex, HttpServletRequest request) {
    ErrorResponse errorResponse = new ErrorResponse(
        HttpStatus.NOT_FOUND, ex.getReason(), ex);
    return ResponseEntity.status(ex.getStatusCode()).body(errorResponse);
  }

  // 서버 내부 오류 - 500 Internal Server Error
  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponse> handleGenericException(Exception ex, HttpServletRequest request) {
    logger.error("서버 내부 오류 발생", ex);

    // 오류 응답 메시지 작성
    ErrorResponse errorResponse = new ErrorResponse(
        HttpStatus.INTERNAL_SERVER_ERROR,
        "서버 내부 오류가 발생했습니다.",
        ex
    );

    // 클라이언트에게 HTTP 500 오류와 함께 오류 메시지 반환
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
  }
}
