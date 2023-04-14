package com.xemphim.WebXemPhim.service;

import com.xemphim.WebXemPhim.common.APIResponse;
import com.xemphim.WebXemPhim.entity.Account;
import com.xemphim.WebXemPhim.entity.FilmPackage;

import java.util.Optional;

public interface ClientService {
    APIResponse purchase(FilmPackage filmPackage, Optional<Account> account);
}
