package com.inu.hackerton.spring.service;

import com.inu.hackerton.spring.model.User;
import com.inu.hackerton.spring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    // signup 메소드에서 비밀번호 암호화 없이 직접 저장
    public String signup(String username, String password) {
        // 이미 존재하는 사용자 체크
        if (userRepository.findByUsername(username).isPresent()) {
            return "이미 존재하는 아이디입니다.";
        }

        // 비밀번호를 암호화하지 않고 그대로 저장
        User user = new User(username, password);
        userRepository.save(user);
        return "회원가입 성공!";
    }

    // login 메소드에서 비밀번호를 그대로 비교
    public String login(String username, String password) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 아이디입니다."));

        // 비밀번호를 암호화하지 않고 그대로 비교
        if (!password.equals(user.getPassword())) {
            return "비밀번호가 일치하지 않습니다.";
        }

        return "로그인 성공!";
    }
}
