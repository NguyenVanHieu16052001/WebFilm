package com.xemphim.WebXemPhim.service.Impl;

import com.xemphim.WebXemPhim.common.APIResponse;
import com.xemphim.WebXemPhim.entity.Account;
import com.xemphim.WebXemPhim.entity.FilmPackage;
import com.xemphim.WebXemPhim.entity.PurchasedFilmPackage;
import com.xemphim.WebXemPhim.entity.PurchasedFilmPackageId;
import com.xemphim.WebXemPhim.repository.AccountRepository;
import com.xemphim.WebXemPhim.repository.PurchasedFilmPackageRepository;
import com.xemphim.WebXemPhim.repository.RoleRepository;
import com.xemphim.WebXemPhim.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PurchasedFilmPackageRepository repository;
    @Override
    public APIResponse purchase(FilmPackage filmPackage, Optional<Account> account) {
        account.get().setRole(roleRepository.findOneByRoleNameIgnoreCase("ROLE_USER"));
        accountRepository.save(account.get());
        APIResponse apiResponse = new APIResponse();
        PurchasedFilmPackage purchasedFilmPackage = new PurchasedFilmPackage();
        PurchasedFilmPackageId id = new PurchasedFilmPackageId();
        id.setAccount(account.get());
        id.setFilmPackage(filmPackage);
        purchasedFilmPackage.setPurchaseDate(new Date());
        purchasedFilmPackage.setId(id);
        purchasedFilmPackage.setExpiration_date(new Date());
        repository.save(purchasedFilmPackage);
        apiResponse.setData("ok");
        return apiResponse;
    }
}
