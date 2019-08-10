package com.demo.induction.aspect;

import com.demo.induction.services.LogService;
import lombok.AllArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Aspect
@Configuration
@AllArgsConstructor
public class EndpointAspect {

    private final LogService service;

    @Around("@annotation(com.demo.induction.aspect.ShouldBeLogged)")
    public Object registerEndpointHits(ProceedingJoinPoint joinPoint) throws Throwable {
        Object proceed = joinPoint.proceed();

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
        service.incrementLogHitCount(location.getPath());
        return proceed;
    }
}