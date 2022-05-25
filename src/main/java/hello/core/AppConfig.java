package hello.core;

import hello.core.Order.OderServiceImpl;
import hello.core.Order.OrderService;
import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;

public class AppConfig {  //애플리케이션의 실제 동작에 필요한 구현 객체를 생성, 생성자를 통해서 주입(연결) 한다.

    /**
     *
     * 명확한 역할 설명, 코드 중복(new memberRepository()) 제거로 리팩터링
     */
/*    public  MemberService memberService(){
        return new MemberServiceImpl(new memberRepository());
    }*/

    public MemberService memberService(){
        return new MemberServiceImpl(memberRepository());
    }

    private MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
/*    public OrderService orderService(){
        return new OderServiceImpl(new memberRepository(),new discountPolicy());
    }*/
    public OrderService orderService(){
        return new OderServiceImpl(memberRepository(),discountPolicy());
    }

   /* public DiscountPolicy discountPolicy(){
        return new FixDiscountPolicy();
    }  */
    public DiscountPolicy discountPolicy(){
        return new RateDiscountPolicy();
    }     // 할인 정책 변경
}
