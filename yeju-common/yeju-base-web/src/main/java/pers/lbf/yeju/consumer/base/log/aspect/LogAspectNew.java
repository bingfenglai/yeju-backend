/*
 * Copyright 2020 赖柄沣 bingfengdev@aliyun.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package pers.lbf.yeju.consumer.base.log.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;

/**
 * TODO
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/1/19 20:01
 */
@Deprecated
@Aspect
@Slf4j
public class LogAspectNew {

    @Pointcut("execution(public * pers.lbf.yeju..*Controller.*(..))")
    public void logAspectNew(){

    }

    /**
     * @description  前置通知
     */
    @Before("logAspectNew()")
    public void doBeforeFunction( JoinPoint joinPoint){
        Class<?> aClass = joinPoint.getTarget().getClass();

        // 获取目标方法的名称
        String methodName = joinPoint.getSignature().getName();

        try {
            // 拿到方法对应的参数类型
            Class<?>[] parameterTypes = ((MethodSignature) joinPoint.getSignature()).getParameterTypes();
            Method method = aClass.getMethod(methodName, parameterTypes);
            Parameter[] parameters = method.getParameters();
            log.info(Arrays.toString(parameters));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        // 获取方法传入参数
        Object[] params = joinPoint.getArgs();
        log.info("==============前置通知执行==========");
        log.info("method {}",methodName);
        log.info("params {}",params);
    }

    /**
     * @description  后置通知
     */
    @After("logAspectNew()")
    public void doAfterFunction(){
        log.info("后置通知执行！");
    }

    /**
     * @description  返回通知
     */
    @AfterReturning("logAspectNew()")
    public void doAfterReturningFunction(){
        log.info("返回通知执行！");
    }

    /**
     * @description  异常通知
     */
    @AfterThrowing("logAspectNew()")
    public void doAfterThrowingFunction(){
        log.info("异常通知执行！");
    }
}
