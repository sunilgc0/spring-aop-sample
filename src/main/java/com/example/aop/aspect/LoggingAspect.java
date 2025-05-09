package com.example.aop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @Around("execution(* com.example.aop.service.*.*(..))")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long duration = System.currentTimeMillis() - start;
        System.out.println("📈 " + joinPoint.getSignature().getName() + " executed in " + duration + " ms"+ result);
        return result;
    }

    @Before("execution(* com.example.aop.service.*.*(..))")
    public void beforeAdvice(JoinPoint joinPoint) {
        System.out.println("🔔 Before method: " + joinPoint.getSignature().getName());
    }

    @AfterReturning(pointcut = "execution(* com.example.aop.service.*.*(..))", returning = "result")
    public void afterReturningAdvice(JoinPoint joinPoint, Object result) {
        System.out.println("📤 Method " + joinPoint.getSignature().getName() + " returned: " + result);
    }

    @After("execution(* com.example.aop.service.*.*(..))")
    public void afterAdvice(JoinPoint joinPoint) {
        System.out.println("✅ After method: " + joinPoint.getSignature().getName());
    }

    @AfterThrowing(pointcut = "execution(* com.example.aop.service.*.*(..))", throwing = "ex")
    public void afterThrowingAdvice(JoinPoint joinPoint, Throwable ex) {
        System.out.println("❌ Exception in method: " + joinPoint.getSignature().getName() + " - " + ex.getMessage());
    }
}