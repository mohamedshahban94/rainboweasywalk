package com.shop.rainboweasywalk.service;


import com.shop.rainboweasywalk.model.UserLogin;
import com.shop.rainboweasywalk.repo.UserLoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

        @Autowired
        private UserLoginRepository loginRepo;

        public Optional<UserLogin> login(String username, String password) {
            return loginRepo.findByUsername(username)
                    .filter(user -> user.getPassword().equals(password));
        }
}

