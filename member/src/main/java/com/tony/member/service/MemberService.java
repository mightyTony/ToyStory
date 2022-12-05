package com.tony.member.service;

import com.tony.member.dto.MemberDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service // 스프링이 관리해주는 서비스 객체(스프링 빈으로 등록)
@RequiredArgsConstructor
public class MemberService {

//    // 리포지토리 생성자 주입
//    private final MemberRepository memberRepository;
   public void save(MemberDTO memberDTO) {
   }
}
