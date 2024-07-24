package hello.core.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.inject.Provider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static org.assertj.core.api.Assertions.assertThat;

public class SingletonWithPrototypeTest1 {

    @Test
    void prototypeFind() {
        AnnotationConfigApplicationContext h = new AnnotationConfigApplicationContext(PrototypeBean.class);
        PrototypeBean prototypeBean1 = h.getBean(PrototypeBean.class);
        prototypeBean1.addCount();
        assertThat(prototypeBean1.getCount()).isEqualTo(1);

        PrototypeBean prototypeBean2 = h.getBean(PrototypeBean.class);
        prototypeBean2.addCount();
        assertThat(prototypeBean2.getCount()).isEqualTo(1);
    }

    @Test
    void singletonWithPrototype() {
        AnnotationConfigApplicationContext h = new AnnotationConfigApplicationContext(clientBean.class,  PrototypeBean.class);

        clientBean clientBean1 = h.getBean(clientBean.class);
        int count1 = clientBean1.logic();
        assertThat(count1).isEqualTo(1);
//
        clientBean clientBean2 = h.getBean(clientBean.class);
        int count2 = clientBean2.logic();
//        assertThat(count2).isEqualTo(2); // prototype scope는 새로 인스턴스를 생성해서 리턴해야 되지만, singleton scope 안에 있어서 그대로 사용됨
        assertThat(count2).isEqualTo(1); // ObjectProvider를 사용하여 새로운 인스턴스 값 리턴 받기
    }

    @Scope("singleton")
    @Component
    static class clientBean {
//        private final PrototypeBean prototypeBean;

        // 생성자가 1개 이므로 Autowired 불필요
//        public clientBean(PrototypeBean prototypeBean) {
//            this.prototypeBean = prototypeBean;
//        }

        /* ObjectProvider or Provider를 사용하여 protoType scope 유지하기 */
        @Autowired
        private Provider<PrototypeBean> provider;

        public int logic() {
            PrototypeBean prototypeBean = provider.get();
            prototypeBean.addCount();
            return prototypeBean.getCount();
        }
    }

    @Scope("prototype")
    @Component
    static class PrototypeBean {
        private int count = 0;

        public void addCount() {
            count++;
        }

        public int getCount() {
            return count;
        }

        @PostConstruct
        public void init() {
            System.out.println("inti " + this);
        }

        @PreDestroy
        public void destroy() {
            System.out.println("destroy " + this);
        }
    }
}
