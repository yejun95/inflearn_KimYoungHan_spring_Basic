package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.order.OrderSerivceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConfigurationSingletonTest {

    @Test
    void configurationTest() {
        ApplicationContext h = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberServiceImpl memberService = h.getBean("memberService", MemberServiceImpl.class);
        OrderSerivceImpl orderService = h.getBean("orderService", OrderSerivceImpl.class);
        MemberRepository memberRepository = h.getBean("memberRepository", MemberRepository.class);

        System.out.println("orderService = " + orderService.getMemberRepository());
        System.out.println("memberService = " + memberService.getMemberRepository());
        System.out.println("memberRepository = " + memberRepository);

    }
}
