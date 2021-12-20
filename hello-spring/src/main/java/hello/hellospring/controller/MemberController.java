package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

// 컴포넌트 스캔 방식 (Controller, Service, Repository에 이미 Component라는 annotation 붙어 있음)
// hello.hellospring을 포함한 하위 패키지들의 @Component들은 모두 스프링 빈으로 자동 등록되게 된다.
@Controller
public class MemberController {

    // 필드 주입 방식 - 별로 안 좋음...
//    @Autowired private MemberService memberService;

    // setter 주입 방식 - Controller 호출 시 setter가 public으로 노출되어 있음
//    private MemberService memberService;
//    @Autowired
//    public void setMemberService(MemberService memberService) {
//        this.memberService = memberService;
//    }

    private final MemberService memberService;
    // new로 생성하지 않는 이유는 얘 자체가 instance 생성해서 사용할 만큼 많은 기능이 있지 않아 그럴 필요가 없음.
    // 하나만 생성해서 공용으로 쓰는 게 좋다.
    // 스프링 컨테이너에 하나만 등록해서 공용으로 쓰면 된다.

    // Autowired: 생성자가 실행될 때 스프링 컨테이너에 있는 memberService와 연결시켜준다. - 생성자 주입
    // = MemberController가 생성될 때 스프링 빈에 등록되어 있는 memberService 객체와 연결시켜줌
    // = dependency injection: 의존관계 주입
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
//        System.out.println("memberService = " + memberService.getClass()); //AOP 프록시 확인 코드
    }

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
