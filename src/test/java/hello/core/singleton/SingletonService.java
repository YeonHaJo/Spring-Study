package hello.core.singleton;

public class SingletonService {

    private static final SingletonService instance = new SingletonService(); //jvm이 실행되면 static 영역을 보고 자기자신인 singleService 객체를 생성
                                                                                //하고 instance에 넣어준다

    public static SingletonService getInstance(){
        return instance;
    }

    private SingletonService(){}  //private access기 때문에 외부에서 new 키워드로 memberService 객체를 생성할 수 없다

    public void logic(){
        System.out.println("싱글톤 객체 로직 호출");

    }
}
