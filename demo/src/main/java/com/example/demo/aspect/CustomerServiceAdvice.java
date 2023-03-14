package com.example.demo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;



@Aspect
@Component

public class CustomerServiceAdvice {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Before("execution( * com.example.demo.service.CustomerService.*(..))")
    public void customerServiceAdvice(JoinPoint joinPoint){
        logger.info("Calling customer database operation in customer service {}",joinPoint);

    }

    @AfterReturning(value = "execution( * com.example.demo.service.CustomerService.*(..))",
            returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result) {
        logger.info("{} returned successfully with value {}", joinPoint, result);
    }

    @After(value = "execution( * com.example.demo.service.CustomerService.*(..))")
    public void after(JoinPoint joinPoint) {
        logger.info("after execution of {}", joinPoint);
    }

    @AfterThrowing(value = "execution( * com.example.demo.service.CustomerService.*(..))",
            throwing = "ex")
    public void afterThrowing(JoinPoint joinPoint, Exception ex) {
        logger.info("{} returned with exception {}", joinPoint, ex.getMessage());
    }

     @Before("com.example.demo.aspect.AopConfig.serviceLayerExecution()")
    public void customerServiceAdvice2(JoinPoint joinPoint){
        logger.info(" POINTCUT: Calling customer database operation in customer service {}",joinPoint);

    }
}
