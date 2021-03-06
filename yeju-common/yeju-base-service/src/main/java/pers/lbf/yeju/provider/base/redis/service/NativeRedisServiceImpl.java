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
package pers.lbf.yeju.provider.base.redis.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import pers.lbf.yeju.common.core.exception.service.ServiceException;
import pers.lbf.yeju.service.interfaces.redis.IRedisService;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @Description TODO
 * @date 2020/12/28 22:32
 */
@Service
public class NativeRedisServiceImpl implements IRedisService {
    private final Logger log = LoggerFactory.getLogger(NativeRedisServiceImpl.class);

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 保存缓存对象
     *
     * @param key   键
     * @param value 值
     */
    @Override
    @Async
    public <T> void addCacheObject(String key, T value) throws ServiceException {
        log.info(String.format("开始存值 %s %s", key, value));
        redisTemplate.opsForValue().set(key, value);
        log.info("存值成功");
    }

    /**
     * 保存缓存对象
     *
     * @param key      键
     * @param value    值
     * @param timeout  过时时间
     * @param timeUnit 时间粒度
     */
    @Override
    @Async
    public <T> void addCacheObject(String key, T value, Long timeout, TimeUnit timeUnit) throws ServiceException {
        log.info(String.format("开始存值 %s %s %d ", key, value, timeout));
        redisTemplate.opsForValue().set(key, value, timeout, timeUnit);
        log.info("存值成功");
    }

    /**
     * 设置超时时间
     *
     * @param key     键
     * @param timeout 过时时间
     * @return b 是否成功
     */
    @Override
    public Boolean expire(String key, long timeout, TimeUnit timeUnit) throws ServiceException {
        log.info(String.format("开始设置缓存时间 %s", key));
        Boolean flag = redisTemplate.expire(key, timeout, timeUnit);
        assert flag != null;
        if (flag.equals(Boolean.TRUE)) {
            return true;
        } else {
            log.warn("key don't not exist！");
            return false;
        }
    }

    /**
     * 获取对象
     *
     * @param key 键
     * @return object
     */
    @Override
    public Object getCacheObject(String key) throws ServiceException {
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * 删除
     *
     * @param key 键
     * @return 是否删除成功
     */
    @Override
    public Boolean deleteObject(String key) throws ServiceException {
        return redisTemplate.delete(key);
    }

    /**
     * 删除多个
     *
     * @param collection key list
     * @return long
     */
    @Override
    public Long deleteObject(Collection<String> collection) throws ServiceException {
        return redisTemplate.delete(collection);

    }

    /**
     * 缓存集合数据
     *
     * @param key      键
     * @param dataList 数据
     * @return long
     */
    @Override
    public <T> Long addCacheList(String key, List<T> dataList) throws ServiceException {
        Long count = redisTemplate.opsForList().rightPush(key, dataList);
        if (count == null) {
            log.warn("redis 添加list 失败");
            return 0L;
        }
        return count;
    }

    /**
     * 获取集合数据
     *
     * @param key 键
     * @return list data
     */
    @Override
    public List<Object> getCacheList(String key) throws ServiceException {
        return redisTemplate.opsForList().range(key, 0, -1);

    }

    /**
     * 缓存set 数据
     *
     * @param key     键
     * @param dataSet set data
     * @return l
     */
    @Override
    public <T> long addCacheSet(String key, Set<T> dataSet) throws ServiceException {
        Long count = redisTemplate.opsForSet().add(key, dataSet);
        if (count == null) {
            log.warn("redis 添加list 失败");
            return 0L;
        }
        return count;
    }

    /**
     * 获取 set data
     *
     * @param key 键
     * @return set data
     */
    @Override
    public Set<Object> getCacheSet(String key) throws ServiceException {
        return redisTemplate.opsForSet().members(key);
    }

    /**
     * 保存 map data
     *
     * @param key     key
     * @param dataMap map data
     */
    @Override
    @Async
    public <T> void addCacheMap(String key, Map<String, T> dataMap) throws ServiceException {
        redisTemplate.opsForHash().putAll(key, dataMap);
    }

    /**
     * get map data
     *
     * @param key key
     * @return map data
     */
    @Override
    public Map<Object, Object> getCacheMap(String key) throws ServiceException {
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * 往hash map中存值
     *
     * @param key   key
     * @param hKey  hkey
     * @param value value
     */
    @Override
    @Async
    public <T> void addCacheMapValue(String key, String hKey, T value) throws ServiceException {
        redisTemplate.opsForHash().put(key, hKey, value);
    }

    /**
     * 获取存在hash map中的值
     *
     * @param key  key
     * @param hKey hkey
     * @return t
     */
    @Override
    public Object getCacheMapValue(String key, String hKey) throws ServiceException {
        return redisTemplate.opsForHash().get(key, hKey);
    }

    /**
     * 获取多个hash map中的值
     *
     * @param key   key
     * @param hKeys hkeys
     * @return t
     */
    @Override
    public List<Object> getMultiCacheMapValue(String key, Collection<Object> hKeys) throws ServiceException {
        return redisTemplate.opsForHash().multiGet(key, hKeys);
    }

    /**
     * 获取缓存的基本对象列表
     *
     * @param pattern 字符串前缀
     * @return 对象列表
     */
    @Override
    public Collection<String> keys(String pattern) throws ServiceException {
        return redisTemplate.keys(pattern);
    }
}
