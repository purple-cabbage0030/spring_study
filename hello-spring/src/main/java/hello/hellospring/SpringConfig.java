package hello.hellospring;

import hello.hellospring.aop.TimeTraceAop;
import hello.hellospring.repository.*;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    //jdbc
//    private DataSource dataSource;
//
//    public SpringConfig(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }

    //jpa
//    @PersistenceContext 원래 스펙에서는 받아야 하는데 없어도 스프링에서 di 해줌
//    private EntityManager em;
//
//    @Autowired
//    public SpringConfig(EntityManager em) {
//        this.em = em;
//    }

    //springdatajpa
    // SpringDataJpaMemberRepository interface만 만들고 JpaRepository extends하면
    // 구현체를 만들어서 스프링 빈에 등록해둠, 그래서 injection 받을 수 있다.
    private final MemberRepository memberRepository;

    @Autowired //생성자 하나인 경우 생략 가능
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // 스프링 빈에 수동으로 등록
    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository); // 스프링 빈에 등록되어 있는 mR을 mS에 넣어준다
    }

//    @Bean
//    public TimeTraceAop timeTraceAop() {
//        return new TimeTraceAop();
//    }

//    @Bean
//    public MemberRepository memberRepository() {
//        return new MemoryMemberRepository();
//        return new JdbcMemberRepository(dataSource);
//        return new JdbcTemplateMemberRepository(dataSource);
//        return new JpaMemberRepository(em);
//    }
}
