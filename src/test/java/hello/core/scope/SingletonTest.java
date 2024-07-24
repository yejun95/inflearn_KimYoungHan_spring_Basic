package hello.core.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import static org.assertj.core.api.Assertions.*;

public class SingletonTest {

    @Test
    void singletonBeanTest() {
        AnnotationConfigApplicationContext h = new AnnotationConfigApplicationContext(SingletonBean.class);

        SingletonBean singletonBean1 = h.getBean(SingletonBean.class);
        SingletonBean singletonBean2 = h.getBean(SingletonBean.class);
        System.out.println("singletonBean1 = " + singletonBean1);
        System.out.println("singletonBean2 = " + singletonBean2);

        assertThat(singletonBean1).isSameAs(singletonBean2); // 싱글톤 빈이기 때문에 같은 인스턴스를 사용

        h.close(); // 싱글톤 빈이기 때문에 종료 메서드까지 정상 호출된다.
    }

    @Scope("singleton")
    static class SingletonBean {
        @PostConstruct
        public void init() {
            System.out.println("singleton init");
        }

        @PreDestroy
        public void destroy() {
            System.out.println("singleton destroy");
        }
    }
}
