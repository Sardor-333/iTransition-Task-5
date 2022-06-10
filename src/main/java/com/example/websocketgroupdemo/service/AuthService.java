package com.example.websocketgroupdemo.service;

import com.example.websocketgroupdemo.entity.User;
import com.example.websocketgroupdemo.payload.req.RegisterDto;
import com.example.websocketgroupdemo.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthService {

    private final UserRepo userRepo;

    public void register(RegisterDto registerDto) {
        User user = userRepo.findByName(registerDto.getName()).orElse(null);
        if (user == null) {
            user = userRepo.save(new User(registerDto.getName()));
        }

        Authentication authentication = new UsernamePasswordAuthenticationToken(user, null, null);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
