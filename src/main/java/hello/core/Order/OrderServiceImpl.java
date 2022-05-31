package hello.core.Order;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
//@RequiredArgsConstructor //final 이 붙은 필드를 보고 그 필드를 파라미터로 사용해 생성자를 자동으로 만들어 준다. (ctrl + f12로 확인)
public class OrderServiceImpl implements OrderService {
    //생성자로 의존성 주입을 해야만! 필드에 final을 붙일 수 있다.->생성자에서만 값을 세팅 할 수 있다. -> 생성자에서 혹시나 값이 세팅되지 않으면 빨간 줄이 쳐저서 실수를 줄일 수 있다.
    private final MemberRepository memberRepository; /// = new MemoryMemberRepository(); AppConfig 설정으로 인한 주석 처리
    //private final DiscountPolicy discountPolicy = new FixDiscountPolicy(); //정책 변경 으로 인한 주석 처리
   // private final DiscountPolicy discountPolicy = new RateDiscountPolicy(); //DIP(구체 클래스에 의존) , OCP(코드변경)위반!!
    private final DiscountPolicy discountPolicy;  //인터페이스에만 의존! 하지만 메소드 호출시 값이 할당 되지 않기 때문에 NullpointException 발생!

    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }  //생성자가 하나일때는 @Autowired 생략 가능!    +) @RequiredArgsConstructor 사용으로 주석처리!

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findByid(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId,itemName,itemPrice,discountPrice);
    }

    //테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }

}
