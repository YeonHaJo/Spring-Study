package hello.core.member;

public class MemberServiceImpl implements MemberService{

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
}
