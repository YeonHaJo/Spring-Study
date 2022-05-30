package hello.core;

import hello.core.Order.OrderServiceImpl;
import hello.core.Order.OrderService;
import hello.core.discount.DiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // -> 싱글톤을 보장 해 줌 @Bean 만 사용해도 빈에 등록 되지만 싱글톤을 보장 해주는 건 @Configuration
public class AppConfig {  //애플리케이션의 실제 동작에 필요한 구현 객체를 생성, 생성자를 통해서 주입(연결) 한다.

    @Bean
    public MemberService memberService(){
        System.out.println("AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public OrderService orderService(){
        System.out.println("AppConfig.orderService");
     //   return new OrderServiceImpl(memberRepository(),discountPolicy());
        return null;

    }

    @Bean
    public MemberRepository memberRepository() {
        System.out.println("AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public DiscountPolicy discountPolicy(){
        return new RateDiscountPolicy();
    }     // 할인 정책 변경
}
