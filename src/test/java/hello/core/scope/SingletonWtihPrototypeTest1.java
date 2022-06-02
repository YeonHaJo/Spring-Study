package hello.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static org.assertj.core.api.Assertions.*;

public class SingletonWtihPrototypeTest1 {

    @Test
    void prototypeFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        prototypeBean1.addCount();
        assertThat(prototypeBean1.getCount()).isEqualTo(1);

        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        prototypeBean2.addCount();
        assertThat(prototypeBean2.getCount()).isEqualTo(1);

        //프로토타입은 요청을 받을 때마다 새로운 빈이 생성 되기 때문에 각각의 빈 필드에 있는 count가 따로 1씩 증가한다.
    }

    @Test
    void singletonClientPrototype(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ClientBean.class,PrototypeBean.class);

        ClientBean clientBean1 = ac.getBean(ClientBean.class);
        int count1 = clientBean1.logic();
        assertThat(count1).isEqualTo(1);

        ClientBean clientBean2 = ac.getBean(ClientBean.class);
        int count2 = clientBean2.logic();
        assertThat(count2).isEqualTo(1);
    }
    @Scope("singleton")//디폴트가 싱글톤이지만 확실한 구분을 위해 사용
    static class ClientBean{

        @Autowired
        private ObjectProvider<PrototypeBean> prototypeBeanProvider; //지정한 빈을 대신 찾아주는 DL 서비스 제공

        public int logic(){
            PrototypeBean prototypeBean = prototypeBeanProvider.getObject(); //getObject : 호출 시 스프링컨테이너에서 빈을 찾아 반환해준다. ->항상 새로운 프로토타입 빈이 생성 됨 
            prototypeBean.addCount();
            int count = prototypeBean.getCount();
            return count;
            // return prototypeBean.getCount();  ctrl+alt+n  한줄로 합치기
        }
    }

    @Scope("prototype")
    static class PrototypeBean{
        private int count = 0;

        public void addCount(){
             count++;
        }

        public int getCount(){
            return count;
        }

        @PostConstruct
        public void init(){
            System.out.println("PrototypeBean.init"+this);
        }

        @PreDestroy
        public void destroy(){
            System.out.println("PrototypeBean.destroy");
            //클라이언트에 빈을 반환하고, 이후 스프링 컨테이너는 생성된 프로토타입 빈을 관리하지 않기 때문에 빈을 관리할 책임은 클라이언트에게 있으므로
            //종료 매서드는 호출되지 않는다.
        }
    }
}
