package com.bipa4.api.sso.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// 존재하는 아이디일 경우
@ResponseStatus(value = HttpStatus.CONFLICT, reason = "ConflictEmail")
public class ConflictEmail extends RuntimeException {

  public ConflictEmail(String message) {
    super(message);
  }
}
