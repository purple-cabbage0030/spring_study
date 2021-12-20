package hello.hellospring.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity //jpa가 관리하는 entity로 매핑
public class Member {

    // pk를 db가 자동 생성해주는 방식 = identity 전략
    // annotation을 통해 db와 매핑(컬럼명과도 매핑 가능)
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 시스템이 지정하는 식별자
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
