package com.bipa4.api.sso.account;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bipa4.api.sso.account.request.AccountRequest;
import com.bipa4.api.sso.account.request.AccountSignRequest;
import com.bipa4.api.sso.account.response.AccountSignInResponse;
import com.bipa4.api.sso.account.response.AccountSignOutResponse;
import com.bipa4.api.sso.account.response.AccountSignUpResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;

// 진입점 및 분배
// 어플리케이션에서 네트워크와 가장 강결합
// 네트워크 부분과 분리 (ROI)
@Api(tags = "ACCOUNT")
@RestController
@RequestMapping("account")
public class AccountController {
  private AccountService accountService;

  public AccountController(AccountService accountService) {
    this.accountService = accountService;
  }

  @Operation(summary = "회원 가입", description = "email, password, username 필수 값")
  @ApiResponse(code = 200, message = "회원가입 성공", response = AccountSignUpResponse.class)
  @PostMapping("sign/up")
  public String signUp(@RequestBody() AccountRequest accountDto) {
    return this.accountService.signUp(accountDto);
  }

  @Operation(summary = "로그인", description = "email, password로 로그인")
  @ApiResponse(code = 200, message = "로그인 성공", response = AccountSignInResponse.class)
  @GetMapping("sign/in")
  public String signIn(@RequestBody() AccountSignRequest accountSignDto) {
    return this.accountService.signIn(accountSignDto);
  }

  @Operation(summary = "로그아웃", description = "jwt setCookie로 max-age=0 혹은 삭제")
  @ApiResponse(code = 200, message = "로그아웃", response = AccountSignOutResponse.class)
  @PostMapping("sign/out")
  public String signOut(String token) {
    return this.accountService.signOut(token);
  }
}
