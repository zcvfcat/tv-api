package com.bipa4.api.tv.account;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "account")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountEntity {
  @Id
  private String email;
  private String password;
  private String userName;
}
