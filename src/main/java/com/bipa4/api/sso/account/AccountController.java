package com.bipa4.api.sso.account;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bipa4.api.sso.account.dto.AccountDto;
import com.bipa4.api.sso.account.dto.AccountSignDto;

// 진입점 및 분배
// 어플리케이션에서 네트워크와 가장 강결합
// 네트워크 부분과 분리 (ROI)
@RestController
@RequestMapping("account")
public class AccountController {
  private AccountService accountService;

  public AccountController(AccountService accountService) {
    this.accountService = accountService;
  }

  @PostMapping("sign/up")
  public String signUp(@RequestBody() AccountDto accountDto) {
    System.out.println("accountDto");
    System.out.println(accountDto);
    return this.accountService.signUp(accountDto);
  }

  @PostMapping("sign/in")
  public String signIn(@RequestBody() AccountSignDto accountSignDto) {
    return this.accountService.signIn(accountSignDto);
  }

  @PostMapping("sign/out")
  public String signOut(String token) {
    return this.accountService.signOut(token);
  }
}
