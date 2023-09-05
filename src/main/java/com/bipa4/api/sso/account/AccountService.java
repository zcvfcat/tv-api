package com.bipa4.api.sso.account;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.bipa4.api.sso.account.dto.AccountDto;
import com.bipa4.api.sso.account.dto.AccountSignDto;
import com.bipa4.api.sso.error.ConflictEmail;
import com.bipa4.api.sso.error.UnauthorizedException;

import lombok.RequiredArgsConstructor;

@Service
public class AccountService {

  private AccountRepository accountRepository;

  public AccountService(AccountRepository accountRepository) {
    this.accountRepository = accountRepository;
  }

  // TODO: 시큐리티 넣어서 보안 구현
  public String signUp(AccountDto accountDto) {
    Optional<AccountEntity> maybeExistAccount = this.accountRepository.findById(accountDto.getEmail());

    if (maybeExistAccount.isPresent())
      throw new ConflictEmail("아이디가 있네요~");

    AccountEntity accountEntity = new AccountEntity(accountDto.getEmail(), accountDto.getPassword(),
        accountDto.getUserName());
    AccountEntity persitedAccountEntity = this.accountRepository.saveAndFlush(accountEntity);

    return "signIn";
  }

  // TODO: 로그인 후 jwt 구현
  public String signIn(AccountSignDto accountSignDto) {
    AccountEntity accountEntity = this.accountRepository.findById(accountSignDto.getEmail()).orElseThrow();
    if (accountSignDto.getPassword() != accountEntity.getPassword()) {
      throw new UnauthorizedException("인증 에러~");
    }

    return "signIn";
  }

  // TODO: 로그아웃 후 jwt
  public String signOut(String token) {
    return "signIn";
  }
}
