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
package pers.lbf.yeju.provider.base.config;

import com.alibaba.fastjson.parser.ParserConfig;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import pers.lbf.yeju.provider.base.redis.serializer.CustomRedisSerializer;

import java.time.Duration;
import java.util.Arrays;

/**
 * TODO
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/1/19 19:45
 */
@Configuration
@EnableCaching
public class CacheConfig {

    @Bean
    @Primary // 当有多个缓存管理器, 表示该缓存器为默认
    public CacheManager cacheManager(RedisConnectionFactory factory) {
        RedisCacheWriter redisCacheWriter = RedisCacheWriter.nonLockingRedisCacheWriter(factory);
        CustomRedisSerializer<Object> serializer = new CustomRedisSerializer<>(Object.class);

        //使用自定义序列化器
        RedisSerializationContext.SerializationPair<Object> pair = RedisSerializationContext.SerializationPair.fromSerializer(serializer);
        RedisCacheConfiguration configuration = RedisCacheConfiguration.defaultCacheConfig();

        RedisCacheConfiguration defaultCacheConfig = configuration.serializeValuesWith(pair);

        // 设置缓存有效时间, 如果没有设置, redis 默认永久有效
        defaultCacheConfig = defaultCacheConfig.entryTtl(Duration.ofDays(30));

        // 初始化管理器
        RedisCacheManager redisCacheManager = new RedisCacheManager(redisCacheWriter, defaultCacheConfig);

        ParserConfig.getGlobalInstance().addAccept("pers.lbf.yeju");

        return redisCacheManager;


    }

    /**
     * 自定义key生成器
     *
     * @return key gen
     */
    @Bean("yejuKeyGenerator")
    public KeyGenerator getKeyGenerator() {
        return (target, method, params) -> {
            // 方法名 + 参数数组 getList[id], 已RedisController.getList(id),
            //最后redis 中 key: value::methodName[param] == list::getList[1]
            // list代表了缓存的名字, getList[1] 表示key, 1 表示传入的 id 的值
            return method.getName() + Arrays.asList(params).toString();
        };
    }


}
