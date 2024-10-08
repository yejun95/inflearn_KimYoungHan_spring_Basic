package hello.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {

    @Test
    public void lifeCycleTest() {
        ConfigurableApplicationContext h = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        NetworkClient client = h.getBean(NetworkClient.class);
        h.close();
    }

    @Configuration
    static class LifeCycleConfig {
//        @Bean(initMethod = "init", destroyMethod = "close") // destroyMethod는 추론기능으로 인해 close, shutdown 같은 메서드를 자동 호출함 -> 현재 close 코드를 지워도 똑같이 돌아감
        @Bean
        public NetworkClient networkClient() {
            NetworkClient networkClient = new NetworkClient(); // url 값이 set 되지 않았음에도 생성자 호출이 됨 -> null 출력
            networkClient.setUrl("http://hello-spring.dev");
            return networkClient;
        }
    }
}
