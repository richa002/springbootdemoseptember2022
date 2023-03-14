package com.example.demo.aspect;

import org.aspectj.lang.annotation.Pointcut;

public class AopConfig {
    @Pointcut("execution( * com.example.demo.service.*.*(..))")
    public void serviceLayerExecution() {}


    //exact method name and argument
    @Pointcut("execution( * com.example.demo.service.CustomerService.getById(Long))")
    public void serviceLayerExecution2() {}

// match method name starting with get
    @Pointcut("execution( * com.example.demo.service.CustomerService.get*(Long))")
    public void serviceLayerExecution3() {}

    // match method name starting with update and any nu,ber of arguments after Long type
    @Pointcut("execution( * com.example.demo.service.CustomerService.update*(Long,..))")
    public void serviceLayerExecution4() {}

}
