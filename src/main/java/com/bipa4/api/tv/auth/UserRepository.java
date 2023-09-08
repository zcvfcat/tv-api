package com.bipa4.api.tv.auth;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bipa4.api.tv.jwt.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findOneWithAuthoritiesByUsername(String username);
}