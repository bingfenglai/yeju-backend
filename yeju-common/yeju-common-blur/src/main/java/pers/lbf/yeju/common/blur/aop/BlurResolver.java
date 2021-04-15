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

package pers.lbf.yeju.common.blur.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pers.lbf.yeju.common.blur.anotations.Blur;
import pers.lbf.yeju.common.blur.strategy.manager.BlurResolveManager;
import pers.lbf.yeju.common.core.exception.service.ServiceException;
import pers.lbf.yeju.common.core.result.IResult;
import pers.lbf.yeju.common.core.result.PageResult;
import pers.lbf.yeju.common.core.result.Result;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 模糊化处理切面类
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/3/26 22:22
 */
@Aspect
@Component
@Slf4j
public class BlurResolver {

    @Autowired
    private BlurResolveManager manager;


    /**
     * 定义切入点 控制层的所有方法
     */
    @Deprecated
    @Pointcut("execution(* pers.lbf..service.*.*(..))")
    public void point() {

    }

    @Pointcut("@annotation(pers.lbf.yeju.common.blur.anotations.DoBlur)")
    public void pointV2() {

    }

    /**
     * 模糊化处理之前
     *
     * @param joinPoint
     */
    @Before("pointV2()")
    public void doBefore(JoinPoint joinPoint) {

    }

    /**
     * 模糊化处理入口
     *
     * @param joinPoint
     * @param value
     */
    @AfterReturning(value = "pointV2()", returning = "value")
    public Object doAfterReturning(JoinPoint joinPoint, Object value) throws ServiceException {
        log.info("----数据模糊处理开始----");
        value = this.dataHandle(value);
        //log.info(value.toString());
        log.info("----数据模糊处理结束----");
        return value;
    }


    @After(value = "pointV2()")
    public void doAfter(JoinPoint joinPoint) {

    }

    /**
     * 数据递归筛选
     *
     * @param value 接口返回值
     */
    private Object dataHandle(Object value) throws ServiceException {
        if (value == null) {
            return null;
        }

        if (value instanceof Result) {
            IResult<?> var = (IResult<?>) value;
            return this.dataHandle(var.getData());
        }
        if (value instanceof PageResult) {
            PageResult var1 = (PageResult) value;
            return this.handleListData(var1.getList());
        }
        if (value instanceof LinkedList) {
            LinkedList varLinkedList = (LinkedList) value;
            return this.handleListData(varLinkedList);
        }
        if (value instanceof ArrayList) {
            ArrayList varList2 = (ArrayList) value;
            return this.handleListData(varList2);
        }

        if (value.getClass().getTypeName().contains("pers.lbf")) {
            return this.handPojoType(value);
        }

        return value;

    }

    /**
     * 模糊化处理属性
     *
     * @param value
     * @return
     */
    private Object handPojoType(Object value) throws ServiceException {
        if (value == null) {
            return null;
        }
        log.info(value.getClass().getName());
        Class<?> aClass = value.getClass();

        //1. 获取对象的所有属性

        Field[] fields = aClass.getDeclaredFields();
        log.info("获取对象的所有属性 {}", Arrays.toString(fields));

        // 2. 遍历所有属性 找出注解标注的属性
        for (Field field : fields) {
            String fieldTypeName = field.getType().getTypeName();
            boolean flag = field.isAnnotationPresent(Blur.class);

            // 如果不存在注解，直接进入下一次遍历
            if (!flag) {
                continue;
            }

            //根据成员属性的不同类型进行处理
            // 只有String类型的数据才能进行模糊化处理
            if (String.class.getTypeName().equals(fieldTypeName)) {
                value = this.handleBlur(field, value);
            }

        }

        return value;
    }

    /**
     * @param field 对象的成员变量
     * @param value 对象
     * @return value
     */
    private Object handleBlur(Field field, Object value) throws ServiceException {

        return manager.blur(value, field);
    }

    private Object handleListData(List<Object> values) {
        if (values != null && !values.isEmpty()) {
            for (int i = 0; i < values.size(); i++) {
                values.set(i, this.handPojoType(values.get(i)));
            }
        }

        return values;
    }


}
