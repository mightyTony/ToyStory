package com.tony.member.service;

import com.tony.member.dto.MemberDTO;
import com.tony.member.entity.MemberEntity;
import com.tony.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service // 스프링이 관리해주는 서비스 객체(스프링 빈으로 등록)
@RequiredArgsConstructor
public class MemberService {

//    // 리포지토리 생성자 주입
   private final MemberRepository memberRepository;
   public void save(MemberDTO memberDTO) {
      // 1, dto -> entity 변환 (MemberEntity 25line)
      // 2. repository의 save 메서드 호출
      MemberEntity memberEntity = MemberEntity.toMemberEntity(memberDTO);
      memberRepository.save(memberEntity);
      // repository의 save 메서드 호출 (조건 : entity 객체를 넘겨줘야 함)
   }

   public MemberDTO login(MemberDTO memberDTO) {
      /*
         1. 회원이 입력한 이메일로 DB에서 조회를 함
         2. DB에서 조회한 비밀번호와 사용자가 입력한 비밀번호가 일치하는지 판단
      */

      Optional<MemberEntity> byMemberEmail = memberRepository.findByMemberEmail(memberDTO.getMemberEmail());
      if(byMemberEmail.isPresent()){
         // 조회 결과가 있다(해당 이메일을 가진 회원 정보가 있다)
         MemberEntity memberEntity = byMemberEmail.get(); //Optional로 감싸진 값을 memberEntity에 저장

         //dto, entity 비번 비교
         if(memberEntity.getMemberPassword().equals(memberDTO.getMemberPassword())){
            // 비밀번호 일치
            // entity -> dto로 변환 후 리턴
            // entity는 Service 단 까지만 사용하게 만들고, Controller에선 dto를 쓰게 하기 위해
            MemberDTO dto = MemberDTO.toMemberDTO(memberEntity);
            return dto;
         } else{
            // 비밀번호 불일치(로그인 실패)
            return null;
         }
      } else {
         // 조회 결과가 없다(해당 이메일을 가진 회원이 없다)
         return null;
      }
   }

   public List<MemberDTO> findAll() {
      List<MemberEntity> memberEntityList = memberRepository.findAll();
      List<MemberDTO> memberDTOList = new ArrayList<>();
      for(MemberEntity memberEntity : memberEntityList){
         memberDTOList.add(MemberDTO.toMemberDTO(memberEntity));
         //MemberDTO memberDTO = MemberDTO.toMemberDTO(memberEntity);
         //memberDTOList.add(memberDTO);
      }
      return memberDTOList;
   }

   public MemberDTO findById(Long id) {
      Optional<MemberEntity> optionalMemberEntity = memberRepository.findById(id);
      if(optionalMemberEntity.isPresent()){
         // 있음
//         MemberEntity memberEntity = optionalMemberEntity.get();
//         MemberDTO memberDTO = MemberDTO.toMemberDTO(memberEntity);
//         return memberDTO;
         return MemberDTO.toMemberDTO(optionalMemberEntity.get());
      }else{
         return null;
      }
   }

   public MemberDTO updateForm(String myEmail) {
      Optional<MemberEntity> optionalMemberEntity = memberRepository.findByMemberEmail(myEmail);
      if(optionalMemberEntity.isPresent()){
         return MemberDTO.toMemberDTO(optionalMemberEntity.get());
      }else{
         return null;
      }
   }

   public void update(MemberDTO memberDTO) {
      memberRepository.save(MemberEntity.toUpdateMemberEntity(memberDTO)); // Id가 없으면 Insert Query를 보내고 ID가 있다면 update
   }

   public void deleteById(Long id) {
      memberRepository.deleteById(id);
   }
}
