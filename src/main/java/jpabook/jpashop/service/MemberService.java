package jpabook.jpashop.service;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.dto.MemberDto;
import jpabook.jpashop.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MemberService {

    @Autowired
    MemberRepository memberRepository;

    //회원 가입
    public Long join(MemberDto memberDto){
        Address address = new Address(
                memberDto.getCity(),
                memberDto.getStreet(),
                memberDto.getZipcode()
        );
        Member member = new Member();
        member.setName(memberDto.getName());
        member.setAddress(address);
        validateDuplicateMember(member);// 중복 회원 검사
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        List<Member> findMembers =
                memberRepository.findByName(member.getName());
        if(!findMembers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    //전체 회원 조회
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findById(Long memberId){
        return memberRepository.findById(memberId);
    }

}
