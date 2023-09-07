package com.bipa4.api.sso.account;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.bipa4.api.sso.account.request.AccountRequest;
import com.bipa4.api.sso.error.ConflictEmail;

class AccountServiceTest {

  private AccountRepository accountRepository;
  private AccountService accountService;

  @BeforeEach
  void setUp() {
    // AccountRepository 목(Mock) 객체 생성
    accountRepository = Mockito.mock(AccountRepository.class);
    accountService = new AccountService(accountRepository);
  }

  @Test
  void testSignUp_NewAccount_Success() {
    // 가상의 계정 정보
    AccountRequest accountDto = new AccountRequest("test@example.com", "password123", "John Doe");

    // repository에서 계정이 없을 때를 가정하여 반환
    Mockito.when(accountRepository.findById(accountDto.getEmail())).thenReturn(Optional.empty());

    // 신규 계정 등록 시도
    String result = accountService.signUp(accountDto);

    // 결과 검증
    Assertions.assertEquals("signIn", result);
    Mockito.verify(accountRepository).saveAndFlush(Mockito.any(AccountEntity.class));
  }

  @Test
  void testSignUp_ExistingAccount_ConflictEmail() {
    // 가상의 계정 정보
    AccountRequest accountDto = new AccountRequest("test@example.com", "password123", "John Doe");

    // repository에서 이미 계정이 존재할 때를 가정하여 반환
    Mockito.when(accountRepository.findById(accountDto.getEmail()))
        .thenReturn(Optional.of(new AccountEntity("test@example.com", "password", "Jane Smith")));

    // 중복된 계정 등록 시도
    Assertions.assertThrows(ConflictEmail.class, () -> accountService.signUp(accountDto));
  }
}
