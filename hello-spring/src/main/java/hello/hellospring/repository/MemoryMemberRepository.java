package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;
import java.util.concurrent.ConcurrentMap;

public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();
    // 실무에서는 동시성 문제가 있을 수 있어서 공유되는 변수의 경우 ConcurrentMap을 쓴다...
    private static long sequence = 0L; // sequence: 0,1,2,... 키값 생성해주는 기능. 실무에서는 long 말고..

    @Override
    public Member save(Member member) {
        member.setId(++sequence); // id값 +1
        store.put(member.getId(), member); // store(map)에 저정
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
        // Optional.ofNullable() - null이 반환될 경우 대비
    }

    @Override
    public Optional<Member> findByName(String name) {
        // 람다?
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny(); // 하나라도 찾기. 없으면 optional에 null이 포함돼서 반환
    }

    @Override
    public List<Member> findAll() {
        // store의 모든 member 반환
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear(); // 데이터 클리어
    }
}
