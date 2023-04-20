package com.xemphim.WebXemPhim.service.Impl;

import com.xemphim.WebXemPhim.common.APIResponse;
import com.xemphim.WebXemPhim.entity.User;
import com.xemphim.WebXemPhim.repository.UserRepository;
import com.xemphim.WebXemPhim.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public APIResponse getUsers() {
        APIResponse apiResponse = new APIResponse();
        apiResponse.setData(userRepository.findAll());
        return apiResponse;
    }
}
