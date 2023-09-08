package com.bipa4.api.tv.account;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.bipa4.api.tv.account.request.AccountRequest;
import com.bipa4.api.tv.account.request.AccountSignRequest;
import com.bipa4.api.tv.error.ConflictEmail;
import com.bipa4.api.tv.error.UnauthorizedException;

@Service
public class AccountService {

  private AccountRepository accountRepository;

  public AccountService(AccountRepository accountRepository) {
    this.accountRepository = accountRepository;
  }

  // TODO: 시큐리티 넣어서 보안 구현
  @Transactional()
  public String signUp(AccountRequest accountRequest) {
    Optional<AccountEntity> maybeExistAccount = this.accountRepository.findById(accountRequest.getEmail());

    if (maybeExistAccount.isPresent())
      throw new ConflictEmail("아이디가 있네요~");

    AccountEntity accountEntity = new AccountEntity(accountRequest.getEmail(), accountRequest.getPassword(),
        accountRequest.getUserName());
    AccountEntity persitedAccountEntity = this.accountRepository.save(accountEntity);

    return "signIn";
  }

  // TODO: 로그인 후 jwt 구현
  public String signIn(AccountSignRequest accountSignDto) {
    AccountEntity accountEntity = this.accountRepository.findById(accountSignDto.getEmail()).orElseThrow();
    if (accountSignDto.getPassword() != accountEntity.getPassword()) {
      throw new UnauthorizedException("인증 에러~");
    }

    return "signIn";
  }

  // TODO: 로그아웃 후 jwt setCookie로 삭제하는 로직 필요
  public String signOut(String token) {
    return "signIn";
  }
}
