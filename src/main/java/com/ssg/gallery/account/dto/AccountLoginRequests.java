package com.ssg.gallery.account.dto;

import lombok.Getter;
import lombok.Setter;

//로그인을 요청할때 사용
@Getter
public class AccountLoginRequests {
    private String loginId;
    private String loginPw;
}