package com.xemphim.WebXemPhim.repository;

import com.xemphim.WebXemPhim.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, String>{

     Account findOneByAccountNameAndPassword(String accountName, String password);

    Optional<Account> findOneByAccountName(String accountName);
}