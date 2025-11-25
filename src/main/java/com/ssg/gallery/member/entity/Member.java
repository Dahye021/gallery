package com.ssg.gallery.member.entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "members")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 50, nullable = false)
    private String name;

    // 🔥 여기가 진짜 중요
    @Column(name = "login_id", length = 50, nullable = false, unique = true)
    private String loginId;   // 필드명 camelCase

    @Column(name = "login_pw", length = 100, nullable = false)
    private String loginPw;   // 필드명 camelCase

    @Column(nullable = false)
    @CreationTimestamp
    private LocalDateTime created;

    public Member() {}

    public Member(String name, String loginId, String loginPw) {
        this.name = name;
        this.loginId = loginId;
        this.loginPw = loginPw;
    }
}