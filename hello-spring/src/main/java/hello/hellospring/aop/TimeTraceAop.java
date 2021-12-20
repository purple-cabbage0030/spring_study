package hello.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect //aop 사용 위한 annotation
@Component //이 방식으로 스프링 빈에 등록해도 되긴 한데 명시적으로 config에 적으면 좋다
public class TimeTraceAop {

    @Around("execution(* hello.hellospring..*(..))") // 적용 대상 설정(보통 패키지 레벨)
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println("START: "+joinPoint.toString());
        try {
            return joinPoint.proceed();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END: "+joinPoint.toString() + timeMs + "ms");
        }
    }
}
