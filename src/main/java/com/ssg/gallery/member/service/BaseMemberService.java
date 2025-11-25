package com.ssg.gallery.member.service;

import com.ssg.gallery.member.entity.Member;
import com.ssg.gallery.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor

public class BaseMemberService implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public void save(String name, String login_id, String login_pw) {
        memberRepository.save(new Member(name, login_id, login_pw));
    }

    @Override
    public Member find(String login_id, String login_pw) {
        Optional<Member> member = memberRepository.findByLoginIdAndLoginPw(login_id,login_pw);
        return member.orElse(null);  //회원 데이터가 있으면 해당 멤버를 리턴하고 없으면 NULL 리턴하여라
    }
}