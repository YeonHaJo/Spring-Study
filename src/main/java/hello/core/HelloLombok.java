package hello.core;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class HelloLombok {

    private String name;
    private int age;


    public static void main(String[] args) {
        HelloLombok helloLombok = new HelloLombok();
        helloLombok.setName("asdf");

        String name = helloLombok.getName();
        System.out.println("name = " + name);
        System.out.println("helloLombok = " + helloLombok);

    }//롬복으로 get,set 메서드 만들필요 없이 어노테이션으로 자동으로 설정되어서 get,set 을 바로 사용할 수 있다. +ToString 등 다양한 기능 많음!굿굿
}
