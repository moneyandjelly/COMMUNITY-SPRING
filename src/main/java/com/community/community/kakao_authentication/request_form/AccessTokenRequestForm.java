package com.community.community.kakao_authentication.request_form;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class AccessTokenRequestForm {
    String code;
}
