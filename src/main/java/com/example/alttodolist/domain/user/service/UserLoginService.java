package com.example.alttodolist.domain.user.service;

import com.example.alttodolist.domain.user.dto.UserLoginRequestDTO;
import com.example.alttodolist.domain.user.entity.UserEntity;
import com.example.alttodolist.domain.user.repository.UserRepository;
import com.example.alttodolist.global.security.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserLoginService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtUtil;

    public String login(UserLoginRequestDTO dto) {
        UserEntity user = userRepository.findByUsername(dto.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 아이디입니다."));


        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 틀렸습니다.");
        }

        return jwtUtil.createToken(user.getUsername(), user.getRole().name());
    }
}
