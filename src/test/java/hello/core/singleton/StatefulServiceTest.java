package hello.core.singleton;

import hello.core.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {

    @Test
    void statefulServiceSingleton(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        //threadA : A사용자 10000원 주문
        statefulService1.order("userA", 10000);
        //threadB : B사용자 20000원 주문
        statefulService2.order("userB", 20000);

        //threadA : 사용자 A 주문 금액 조회
        int price = statefulService1.getPrice();
        System.out.println("price=" + price);

        //결과 10000원을 기대했으나 같은 객체를 공유 하므로 b가 중간에 주문하면서 금액이 20000원이 되어 출력 됨

        //검증
        Assertions.assertThat(statefulService1.getPrice()).isEqualTo(20000);

        //해결방법 : 전역변수로 지정 되있는 Price를 삭제 하고 order 메서드에서 바로 Price를 리턴해서 지역변수로 만들면 공유가 되지 않는다.
    }
    static class TestConfig{

        @Bean
        public StatefulService statefulService(){
            return new StatefulService();
        }
    }
}