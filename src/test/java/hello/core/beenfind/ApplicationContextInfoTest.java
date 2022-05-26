package hello.core.beenfind;

import hello.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextInfoTest {
    
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
    
    @Test
    @DisplayName("모든 빈 출력하기")   //스프링 내부적으로 확장하기 위한 빈들도 같이 출력
    void findAllBeen(){
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            Object bean = ac.getBean(beanDefinitionName);  //타입 지정을 안했기 때문에 object 로 지정됨
            System.out.println("Name = " + beanDefinitionName + "object =" +bean);

        }
    }   @Test
    @DisplayName("애플리케이션 빈 출력하기")  //내가 등록한 빈들만 출력
    void findApplicationBeen(){
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);//getBeanDefinition -> bean에 대한 메타데이터 (정보)

            //Role ROLE_APPLICATION : 직접 등록한 빈
            //Role ROLE_INFRASTRUCTURE : 스프링이 내부에서 사용하는 빈
            if(beanDefinition.getRole()==BeanDefinition.ROLE_APPLICATION) {//->스프링 내부등록을 위한 빈이 아닌 개발자가 개발을 위해 등록한 빈 또는 외부 라이브러리의 빈
                Object bean = ac.getBean(beanDefinitionName);  //타입 지정을 안했기 때문에 object 로 지정됨
                System.out.println("Name = " + beanDefinitionName + "object =" +bean);
            }
        }
    }
}
