package com.xemphim.WebXemPhim.dto.mapper;

import com.xemphim.WebXemPhim.dto.AccountDTO;
import com.xemphim.WebXemPhim.entity.Account;

public class AccountMapper {
    private static AccountMapper INSTANCE;
    public static AccountMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new AccountMapper();
        }
        return INSTANCE;
    }
    public AccountDTO toDTO(Account account) {
        AccountDTO dto = new AccountDTO();
        dto.setAccountName(account.getAccountName());
        dto.setUserName(account.getUser().getUserName());
        dto.setRole(account.getRole().getRoleName());
        dto.setEmail(account.getUser().getEmail());
        dto.setPhoneNumber(account.getUser().getPhoneNumber());
        return dto;
    }
}
