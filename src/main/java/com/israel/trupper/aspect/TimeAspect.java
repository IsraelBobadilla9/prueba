package com.israel.trupper.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TimeAspect {
    
    private static final Logger log = LoggerFactory.getLogger(TimeAspect.class);

    @Around("execution (* com.israel.trupper.controller..*(..)) || execution (* com.israel.trupper.service..*(..))")
    public Object measureTime(ProceedingJoinPoint joinPoint) throws Throwable{
        long start  = System.currentTimeMillis();
        try {
            return joinPoint.proceed();
            
        } finally {
            long end  = System.currentTimeMillis() -start;
            log.info("[Tiempo de ejecucion] {}.{} ejecucion en {} ms",joinPoint.getSignature().getDeclaringType().getSimpleName(), joinPoint.getSignature().getName(),end);
        }
    }

}
