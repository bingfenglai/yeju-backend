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
package pers.lbf.yeju.common.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Map;

/**
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @Description TODO
 * @date 2020/11/30 14:48
 */
public class JsonUtils {

    public static final ObjectMapper MAPPER = new ObjectMapper();

    public static String toString(Object obj) throws JsonProcessingException {
        if (obj == null) {
            return null;
        }
        if (obj.getClass() == String.class) {
            return (String) obj;
        }

        return MAPPER.writeValueAsString(obj);

    }

    public static <T> T toBean(String json, Class<T> tClass) throws JsonProcessingException {

        return MAPPER.readValue(json, tClass);

    }

    public static <E> List<E> toList(String json, Class<E> eClass) throws JsonProcessingException {

        return MAPPER.readValue(json, MAPPER.getTypeFactory().constructCollectionType(List.class, eClass));

    }

    public static <K, V> Map<K, V> toMap(String json, Class<K> kClass, Class<V> vClass) throws JsonProcessingException {

        return MAPPER.readValue(json, MAPPER.getTypeFactory().constructMapType(Map.class, kClass, vClass));

    }

    public static <T> T nativeRead(String json, TypeReference<T> type) throws JsonProcessingException {

        return MAPPER.readValue(json, type);

    }

    public static <T> String toJson(T t) throws JsonProcessingException {
        return MAPPER.writeValueAsString(t);
    }

    private JsonUtils() {
    }


}
