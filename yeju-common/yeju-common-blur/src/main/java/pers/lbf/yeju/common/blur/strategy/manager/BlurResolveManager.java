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

package pers.lbf.yeju.common.blur.strategy.manager;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import pers.lbf.yeju.common.blur.anotations.Blur;
import pers.lbf.yeju.common.blur.constants.ParameterConstants;
import pers.lbf.yeju.common.blur.strategy.BlurStrategy;
import pers.lbf.yeju.common.blur.strategy.impl.NameBlurStrategy;
import pers.lbf.yeju.common.blur.strategy.impl.PhoneNumberBlurStrategy;
import pers.lbf.yeju.common.core.exception.service.ServiceException;
import pers.lbf.yeju.common.core.status.enums.ServiceStatusEnum;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 模糊化处理管理器
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/3/27 14:26
 */
@Component
@Slf4j
public class BlurResolveManager {

    private final static ConcurrentHashMap<ParameterConstants, BlurStrategy> blurStrategyMap;

    static {
        blurStrategyMap = new ConcurrentHashMap<>();
        blurStrategyMap.put(ParameterConstants.PHONE_NUMBER, PhoneNumberBlurStrategy.INSTANCE);
        blurStrategyMap.put(ParameterConstants.NAME, NameBlurStrategy.INSTANCE);
    }

    public Object blur(Object obj, Field field) throws ServiceException {
        Blur blur = field.getAnnotation(Blur.class);
        Class clz = obj.getClass();
        String name = this.firstUpperCase(field.getName());
        // 设置操作权限为true
        //field.setAccessible(true);

        //获取属性的getter方法
        try {


            Method getMethod = clz.getMethod("get" + name);
            Object value = getMethod.invoke(obj);
            Object blurValue = this.doBlur(value, blur.value());

            // 获取属性的setter方法
            Method setMethod = clz.getMethod("set" + name, field.getType());
            setMethod.invoke(obj, blurValue);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            log.error(String.valueOf(e));
            throw ServiceException.getInstance(e.getMessage(), ServiceStatusEnum.UNKNOWN_ERROR.getCode());
        }

        return obj;
    }

    private Object doBlur(Object value, ParameterConstants parameterConstants) {

        boolean flag = blurStrategyMap.containsKey(parameterConstants);

        if (flag) {
            BlurStrategy blurStrategy = blurStrategyMap.get(parameterConstants);
            return blurStrategy.doBlur((String) value);
        } else {
            log.info("没有找到对应的模糊处理策略 {}", parameterConstants.toString());
        }

        return value;

    }

    private String firstUpperCase(String name) {

        return StringUtils.replaceChars(name, name.substring(0, 1), name.substring(0, 1).toUpperCase());
    }
}
