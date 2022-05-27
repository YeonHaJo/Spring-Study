package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;

public class SingletonTest {

    @Test
    @DisplayName("스프리 없는 순수한 DI 컨테이너")
    void pureCOntainer() {
        AppConfig appConfig = new AppConfig();
        // 1. 조회:호출 할 때 마다 객체를 생성
        MemberService memberService1 = appConfig.memberService();

        // 2. 조회:호출 할 때 마다 객체를 생성
        MemberService memberService2 = appConfig.memberService();

        //참조 값이 다른 것을 확인
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        //memberSerivce1 != memberService2
        assertThat(memberService1).isNotEqualTo(memberService2);

        //결과: 각기 다른 새로운 객체가 생성돼 메모리에 올라가게 된다. ->메모리 낭비


    }

    @Test
    @DisplayName("singletone 패턴을 적용한 객체 사용")
    void singletonService() {
        //    new SingletonService();
        //privcete access기 때문에 실패
        SingletonService sinletonService1 = SingletonService.getInstance();
        SingletonService sinletonService2 = SingletonService.getInstance();

        System.out.println("SingletonService1 =" + sinletonService1);
        System.out.println("SingletonService2 =" + sinletonService2);
        //결과 : 같은 주소의 객체 2개가 생성 됨
        assertThat(sinletonService1).isSameAs(sinletonService2);

        //isSameAs: 주소값 비교 메서드  isEqualTo: 대상 내용자체 비교
    }

    @Test
    @DisplayName("스프링 컨테이너와 싱클톤")
    void springContainer(){
//        AppConfig appConfig = new AppConfig();

        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService1 = ac.getBean("memberService",MemberService.class);
        MemberService memberService2 = ac.getBean("memberService",MemberService.class);

        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        assertThat(memberService1).isSameAs(memberService2);
    }
}
