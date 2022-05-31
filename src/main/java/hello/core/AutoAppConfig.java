package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration //열어보면 @Componant 가 있음
@ComponentScan(
    //    basePackages = "hello.core.member", //지정한 패키지와 그 하위 패키지만 스캔&등록
    //    basePackageClasses = AutoAppConfig.class, //지정한 클래스의 패키지 스캔&등록
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
        //등록하는 bean 들 중에 빼야 할 bean 지정,  Configuration.class 빼는 이유 : AppConfig class 는 이미 수동으로 등록 해 있기 때문에 충돌이 일어남 방지(@
)//@ComponentScan : @Component Annotation 이 붙은 모든 클래스를 찾아서 bean 으로 등록해줌

public class AutoAppConfig {

}
