package hello.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {

    @Test
    public void lifeCycleTest(){
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        NetworkClient client = ac.getBean(NetworkClient.class);
        ac.close();
    }
    @Configuration
    static class LifeCycleConfig{
        @Bean
        public NetworkClient networkClient(){
            NetworkClient networkClient = new NetworkClient();//여기까지  테스트가 수행 될때까지 url 정보는 없다.(단일책임원칙)
            networkClient.setUrl("http://hello_spring.dev");//초기화되는 시점
            return networkClient;
        }
    }
}
