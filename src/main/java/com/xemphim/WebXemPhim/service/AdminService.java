package com.xemphim.WebXemPhim.service;

import com.xemphim.WebXemPhim.common.APIResponse;
import com.xemphim.WebXemPhim.entity.User;

public interface AdminService {
    User addUser(User user);

    APIResponse getUsers();
}
