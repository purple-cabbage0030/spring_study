package hello.hellospring.repository;

import hello.hellospring.domain.Member;


import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{

    private final EntityManager em;
    // 스프링부트가 entitymanager 자동으로 생성(properties 설정 포함), db와도 연결해서 만들어 둠
    // 만들어진 객체를 injection 받으면 된다

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);//find(조회할 타입, 식별자)
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();

        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        //jpql: 객체(Entity) 대상으로 query 날리면 sql로 번역됨
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }
}
