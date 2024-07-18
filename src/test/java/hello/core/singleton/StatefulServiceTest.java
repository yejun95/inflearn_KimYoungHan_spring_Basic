package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {

    @Test
    @DisplayName("싱글톤 Statefull 테스트")
    void statefuloServiceSingleton() {
        ApplicationContext h = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = h.getBean(StatefulService.class);
        StatefulService statefulService2 = h.getBean(StatefulService.class);

        //ThreadA : A사용자 10000원 주문
        int userA = statefulService1.order("A", 10000);
        //ThreadB : B사용자 20000원 주문
        int userB = statefulService2.order("B", 20000);

        //ThreadA 사용자의 주문 금액 조회
//        int price = statefulService1.getPrice();
        System.out.println("price =" + userA);
        System.out.println(statefulService1);
        System.out.println(statefulService2);
//        assertThat(statefulService1.getPrice()).isEqualTo(20000);
    }

    static class TestConfig {
        @Bean
        static StatefulService statefulService() {
            return new StatefulService();
        }
    }

}