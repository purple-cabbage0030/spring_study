package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    // rest api - url mapping
    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "spring");
        // rendering할 템플릿 이름(ViewName) 지정 - viewResolver가 화면을 찾아서 처리
        return "hello";
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(value = "name", required = false) String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }

    // api 방식
    @GetMapping("hello-string")
    // http 통신의 header와 body 가운데 body에 아래 내용을 직접 넣겠다는 설정
    @ResponseBody
    public String helloString(@RequestParam("name") String name) {
        return  "hello " + name;
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello; // 객체 반환 시 자동으로 json 형태 데이터로 전환 (HttpMessageConverter)
    }

    static class Hello {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
