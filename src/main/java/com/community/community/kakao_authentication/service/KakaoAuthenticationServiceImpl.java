package com.community.community.kakao_authentication.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.community.community.kakao_authentication.repository.KakaoAuthenticationRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class KakaoAuthenticationServiceImpl implements KakaoAuthenticationService {
    final private KakaoAuthenticationRepository kakaoAuthenticationRepository;

    @Override
    public String getLoginLink() {
        return this.kakaoAuthenticationRepository.getLoginLink();
    }

    @Override
    public Map<String, Object> requestAccessToken(String code) {
        return this.kakaoAuthenticationRepository.getAccessToken(code);
    }

    @Override
    public Map<String, Object> requestUserInfo(String accessToken) {
        return this.kakaoAuthenticationRepository.getUserInfo(accessToken);
    }
}
