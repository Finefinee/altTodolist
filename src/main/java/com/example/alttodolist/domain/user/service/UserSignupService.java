package com.example.alttodolist.domain.user.service;


import com.example.alttodolist.domain.user.entity.role.UserRole;
import com.example.alttodolist.domain.user.dto.UserSignupRequestDTO;
import com.example.alttodolist.domain.user.entity.UserEntity;
import com.example.alttodolist.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserSignupService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void signup(UserSignupRequestDTO dto) {
        // 1. 아이디 중복 체크
        if (userRepository.existsByUsername(dto.getUsername())) {
            throw new IllegalArgumentException("이미 사용 중인 아이디입니다.");
        }
        // 2. 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(dto.getPassword());

        // 3. 엔티티 생성 및 권한 설정 (role은 항상 USER로 고정)
        UserEntity user = new UserEntity();
        user.setUsername(dto.getUsername());
        user.setPassword(encodedPassword);
        user.setRole(UserRole.USER);

        // 4. 저장
        userRepository.save(user);
    }
}
