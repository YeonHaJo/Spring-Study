package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService{
    @Autowired //자동으로 의존관계 주입 ==ac.getBean(MemberRepository.class)
    public MemberServiceImpl(MemberRepository memberRepository) {  //생성자를 통해서 어떤 구현체가 들어갈지 선택
         this.memberRepository = memberRepository;
    }

    private final MemberRepository memberRepository; //= new MemoryMemberRepository(); Appconfig 설정으로 인한 주석 처리

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findByid(memberId);
    }

    //테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
