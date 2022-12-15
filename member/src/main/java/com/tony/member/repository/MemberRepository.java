package com.tony.member.repository;

import com.tony.member.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> { // Jpa리포지토리<'엔티티 클래스 이름', 'pk의 자료형'>
    // 이메일로 회원 정보 조회 (select * from member_table where member_email=?)
    Optional<MemberEntity> findByMemberEmail(String memberEmail);
}
