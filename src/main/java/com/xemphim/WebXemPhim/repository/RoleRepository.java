package com.xemphim.WebXemPhim.repository;

import com.xemphim.WebXemPhim.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer>{

     Role findOneByRoleNameIgnoreCase(String roleName);
}
