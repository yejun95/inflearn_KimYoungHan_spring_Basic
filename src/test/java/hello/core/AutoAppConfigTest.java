package hello.core;

import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class AutoAppConfigTest {

    @Test
    @DisplayName("AutoAppConfig테스트")
    void basicScan() {
        AnnotationConfigApplicationContext h = new AnnotationConfigApplicationContext(AutoAppConfig.class);
        MemberService memberService = h.getBean(MemberService.class);
        assertThat(memberService).isInstanceOf(MemberService.class);
    }
}