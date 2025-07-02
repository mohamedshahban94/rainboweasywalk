package com.shop.rainboweasywalk.repo;

import com.shop.rainboweasywalk.model.UserLogin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserLoginRepository extends JpaRepository<UserLogin, Long> {
        Optional<UserLogin> findByUsername(String username);
}

