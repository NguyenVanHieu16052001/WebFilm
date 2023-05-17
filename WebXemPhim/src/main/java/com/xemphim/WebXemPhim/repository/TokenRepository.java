package com.xemphim.WebXemPhim.repository;

import com.xemphim.WebXemPhim.entity.Account;
import com.xemphim.WebXemPhim.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token,Integer> {
    List<Token> findAllValidTokenByAccount(Account account);
    Optional<Token> findFirstByAccountOrderByIdDesc(Optional<Account> account);

    Token findByToken(String token);
}
