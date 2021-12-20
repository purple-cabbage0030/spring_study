package hello.hellospring;

import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    // 스프링 빈에 수동으로 등록
    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository()); // 스프링 빈에 등록되어 있는 mR을 mS에 넣어준다
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}
