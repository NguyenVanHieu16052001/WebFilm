package com.xemphim.WebXemPhim.repository;

import com.xemphim.WebXemPhim.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer>{

    User findOneByEmailAndPhoneNumber(String email, String phoneNumber);

    User findOneByEmail(String email);
}
