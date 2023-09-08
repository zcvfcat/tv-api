package com.bipa4.api.tv.account.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// client와 was와의 연결
// validation - 데이터 무결성 체크

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AccountRequest {
  // 식별자 (identifier)
  @Email(message = "메일 입력은 필수 입니다.")
  @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,6}$", message = "이메일 형식에 맞지 않습니다.")
  private String email;

  @NotEmpty(message = "비밀번호 입력은 필수 입니다.")
  @Size(min = 5, message = "비밀번호는 최소 5자 이상이어야 합니다.")
  private String password;

  @NotEmpty(message = "이름 입력은 필수 입니다.")
  @Size(min = 2, message = "두 자리 이상이어야 합니다.")
  private String userName;
}
