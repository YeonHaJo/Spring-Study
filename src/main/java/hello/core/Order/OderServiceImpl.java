package hello.core.Order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OderServiceImpl implements OrderService {

    private final MemberRepository memberRepository; /// = new MemoryMemberRepository(); AppConfig 설정으로 인한 주석 처리
    //private final DiscountPolicy discountPolicy = new FixDiscountPolicy(); //정책 변경 으로 인한 주석 처리
   // private final DiscountPolicy discountPolicy = new RateDiscountPolicy(); //DIP(구체 클래스에 의존) , OCP(코드변경)위반!!
    private final DiscountPolicy discountPolicy;  //인터페이스에만 의존! 하지만 메소드 호출시 값이 할당 되지 않기 때문에 NullpointException 발생!

    public OderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findByid(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId,itemName,itemPrice,discountPrice);
    }
}
