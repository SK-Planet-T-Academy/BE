package com.asac7_hackathon.hackathon.domain.users.controller.exception;

import com.asac7_hackathon.hackathon.domain.users.controller.dto.DateFormat;
import java.time.LocalDateTime;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ErrorResponse {
  @DateFormat
  private final LocalDateTime timestamp;
  private final int status;
  private final String error;
  private final String message;

  public ErrorResponse(HttpStatus status, String message) {
    this.timestamp = LocalDateTime.now();
    this.status = status.value();
    this.error = status.getReasonPhrase();
    this.message = message;
  }
}
