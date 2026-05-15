package rikkei_ptit.javawebservicesession14.hackathon.aspect;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Slf4j
@Component
public class LoggingAspect {
    @Before("execution(* rikkei_ptit.javawebservicesession14.hackathon.service.*.create*(..)) || execution(* rikkei_ptit.javawebservicesession14.hackathon.service.*.update*(..))")
    public void logBeforeEveryAddOrUpdate(JoinPoint joinPoint) {
        log.info("Method execution started: {} with arguments: {}", joinPoint.getSignature().getName(), Arrays.stream(joinPoint.getArgs()).map(String::valueOf).reduce((a, b) -> a + ", " + b).orElse(""));
    }
}
