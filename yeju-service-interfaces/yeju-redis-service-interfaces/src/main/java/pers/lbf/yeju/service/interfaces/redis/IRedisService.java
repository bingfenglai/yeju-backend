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
package pers.lbf.yeju.service.interfaces.redis;

import pers.lbf.yeju.common.core.exception.service.ServiceException;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * redis连接存取值服务服务
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @Description TODO
 * @date 2020/12/3 9:16
 */
public interface IRedisService {

    /**
     * 保存缓存对象
     *
     * @param key   键
     * @param value 值
     * @param <T>   形式化参数
     */
    <T> void addCacheObject(String key, T value) throws ServiceException;

    /**
     * 保存缓存对象
     *
     * @param key      键
     * @param value    值
     * @param timeout  过时时间
     * @param timeUnit 时间粒度
     * @param <T>      形式化参数类型
     */
    <T> void addCacheObject(String key, T value, final Long timeout, final TimeUnit timeUnit) throws ServiceException;


    /**
     * 设置超时时间
     *
     * @param key     键
     * @param timeout 过时时间
     * @return b 是否成功
     */
    Boolean expire(String key, long timeout, TimeUnit timeUnit) throws ServiceException;

    /**
     * 获取对象
     *
     * @param <T> 形式化参数
     * @param key 键
     * @return object
     */
    Object getCacheObject(String key) throws ServiceException;


    /**
     * 删除
     *
     * @param key 键
     * @return 是否删除成功
     */
    Boolean deleteObject(String key) throws ServiceException;


    /**
     * 删除多个
     *
     * @param collection key list
     * @return long
     */
    Long deleteObject(Collection<String> collection) throws ServiceException;


    /**
     * 缓存集合数据
     *
     * @param <T>      形式化参数类型
     * @param key      键
     * @param dataList 数据
     * @return long
     */
    <T> Long addCacheList(String key, List<T> dataList) throws ServiceException;

    /**
     * 获取集合数据
     *
     * @param <T> 形式化参数类型
     * @param key 键
     * @return list data
     */
    List<Object> getCacheList(String key) throws ServiceException;


    /**
     * 缓存set 数据
     *
     * @param key     键
     * @param dataSet set data
     * @param <T>     形式化参数类型
     * @return l
     */
    <T> long addCacheSet(String key, Set<T> dataSet) throws ServiceException;


    /**
     * 获取 set data
     *
     * @param <T> 形式化参数类型
     * @param key 键
     * @return set data
     */
    Set<Object> getCacheSet(String key) throws ServiceException;


    /**
     * 保存 map data
     *
     * @param key     key
     * @param dataMap map data
     * @param <T>     t
     */
    <T> void addCacheMap(String key, final Map<String, T> dataMap) throws ServiceException;


    /**
     * get map data
     *
     * @param <T> t
     * @param key key
     * @return map data
     */
    Map<Object, Object> getCacheMap(String key) throws ServiceException;


    /**
     * 往hash map中存值
     *
     * @param key   key
     * @param hKey  hkey
     * @param value value
     * @param <T>   t
     */
    <T> void addCacheMapValue(String key, String hKey, final T value) throws ServiceException;


    /**
     * 获取存在hash map中的值
     *
     * @param <T>  t
     * @param key  key
     * @param hKey hkey
     * @return t
     */
    Object getCacheMapValue(String key, String hKey) throws ServiceException;


    /**
     * 获取多个hash map中的值
     *
     * @param <T>   t
     * @param key   key
     * @param hKeys hkeys
     * @return t
     */
    List<Object> getMultiCacheMapValue(String key, Collection<Object> hKeys) throws ServiceException;


    /**
     * 获取缓存的基本对象列表
     *
     * @param pattern 字符串前缀
     * @return 对象列表
     */
    Collection<String> keys(String pattern) throws ServiceException;
}
