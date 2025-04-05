package com.example.aop.aspect;

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
        System.out.println("📈 " + joinPoint.getSignature() + " executed in " + duration + " ms");
        return result;
    }

    @Before("execution(* com.example.aop.service.*.*(..))")
    public void beforeAdvice(org.aspectj.lang.JoinPoint joinPoint) {
        System.out.println("🔔 Before method: " + joinPoint.getSignature().getName());
    }

    @After("execution(* com.example.aop.service.*.*(..))")
    public void afterAdvice(org.aspectj.lang.JoinPoint joinPoint) {
        System.out.println("✅ After method: " + joinPoint.getSignature().getName());
    }

    @AfterReturning(pointcut = "execution(* com.example.aop.service.*.*(..))", returning = "result")
    public void afterReturningAdvice(org.aspectj.lang.JoinPoint joinPoint, Object result) {
        System.out.println("📤 Method " + joinPoint.getSignature().getName() + " returned: " + result);
    }

    @AfterThrowing(pointcut = "execution(* com.example.aop.service.*.*(..))", throwing = "ex")
    public void afterThrowingAdvice(org.aspectj.lang.JoinPoint joinPoint, Throwable ex) {
        System.out.println("❌ Exception in method: " + joinPoint.getSignature().getName() + " - " + ex.getMessage());
    }
}