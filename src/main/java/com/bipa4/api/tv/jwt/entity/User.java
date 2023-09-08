package com.bipa4.api.tv.jwt.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity // DB의 테이블과 1:1 매핑되는 객체
@Table(name = "users")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

  @JsonIgnore
  @Id // primary key
  @Column(name = "user_id")
  // 자동 증가 되는
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long userId;

  @Column(name = "username", length = 50, unique = true)
  private String username;

  @JsonIgnore
  @Column(name = "password", length = 100)
  private String password;

  @Column(name = "nickname", length = 50)
  private String nickname;

  @JsonIgnore
  @Column(name = "activated")
  private boolean activated;

  @ManyToMany
  @JoinTable(name = "user_authority", joinColumns = {
      @JoinColumn(name = "user_id", referencedColumnName = "user_id") }, inverseJoinColumns = {
          @JoinColumn(name = "authority_name", referencedColumnName = "authority_name") })
  private Set<Authority> authorities;
}