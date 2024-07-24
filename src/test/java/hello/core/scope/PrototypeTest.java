package hello.core.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import static org.assertj.core.api.Assertions.*;

public class PrototypeTest {

    @Test
    void prototypeBeanFind() {
        AnnotationConfigApplicationContext h = new AnnotationConfigApplicationContext(PrototypeBean.class);
        System.out.println("싱글톤1 실행");
        PrototypeBean prototypeBean1 = h.getBean(PrototypeBean.class);
        System.out.println("싱글톤2 실행");
        PrototypeBean prototypeBean2 = h.getBean(PrototypeBean.class);

        assertThat(prototypeBean1).isNotSameAs(prototypeBean2); // 프로토타입 빈은 인스턴스를 요청마다 만들어주기 때문에 다른 주소값을 가진다.

        h.close(); // 스코프 빈이기 때문에 종료 메서드까지 호출 불가능

        // 만약 스코프 빈에서 종료 메서드를 실행시키고 싶으면 아래와 같이 수동으로 호출해야함
        prototypeBean1.destroy();
        prototypeBean2.destroy();
    }

    @Scope("prototype")
    static class PrototypeBean {
        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean init");
        }

        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBen destroy");
        }
    }
}
