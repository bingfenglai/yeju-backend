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
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @Description TODO
 * @date 2021/1/1 16:08
 */
@Component
@Aspect
@Slf4j
public class LogAspect {

    private  final Logger logger = LoggerFactory.getLogger(LogAspect.class);


    /**
     * 定义切入点
     */
    @Pointcut("@annotation(pers.lbf.yeju.consumer.base.log.anotation.Log)")
    public void logAspect() {

    }

    /**
     * 在处理完请求后执行
     */
    @AfterReturning(pointcut = "logAspect()",returning = "object")
    public void doAfter(JoinPoint joinPoint, Object object){
        handle(joinPoint, object);
    }

    /**
     * 出现异常后执行
     * @param joinPoint
     * @param e
     */
    @AfterThrowing(pointcut = "logAspect()",throwing = "e")
    public void doAfterThrowing(JoinPoint  joinPoint, Exception e){
        handle(joinPoint,e);

    }

    /**
     * 日志处理逻辑
     * @param joinPoint
     * @param object
     */
    public void handle(JoinPoint joinPoint, Object object){
        log.info(joinPoint.toString());
    }

    /**
     * 日志处理逻辑
     * @param joinPoint
     * @param e
     */
    public void handle(JoinPoint joinPoint, Exception e) {
        log.warn(joinPoint.toString());

    }
}
