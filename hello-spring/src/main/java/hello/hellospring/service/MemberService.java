// 비즈니스 메소드 (서비스)
package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

// 스프링 컨테이너에 초기 run 시에 MemberService를 등록하기 위한 annotation
//@Service

//jpa 쓸 때 데이터 저장/변경 시에는 항상 transaction이 있어야 한다
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    // test 코드와 동일 객체 사용하기 위해 constructor 사용 - 생성자 주입 방식
    // dependency injection - autowired
//    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원가입
     */
    public Long join(Member member) {
        validateDuplicateMember(member); // 중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
//        Optional<Member> result = memberRepository.findByName(member.getName());
        // ifPresent(): null 아닌 어떤 값이 있으면 내부 로직이 동작
        // Optional 이기 때문에 가능한 method
//        result.ifPresent(m -> {
//            throw new IllegalStateException("이미 존재하는 회원입니다.");
//        });
        // Optional 반환하는 코드보단 아래 코드 형태를 많이 쓴다.
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
