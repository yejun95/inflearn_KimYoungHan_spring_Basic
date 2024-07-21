package hello.core;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HelloLombok {
    private String name;
    private int age;

    public static void main(String[] args) {
        HelloLombok lombokSet = new HelloLombok();
        lombokSet.setName("lombok으로 set을 해주자.");

        String name = lombokSet.getName();
        System.out.println("name = " + name);
    }
}
