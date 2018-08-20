package com.lht.demo.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface IHashCache {
    /**
     * 查看哈希表hKey中,给定域hashKey是否存在
     *
     * @description

     * @param hashKey
     * @return
     */
    boolean hashCheckHxists(String hKey, String hashKey);

    /**
     * 查看哈希表hKey中,给定域hashKey的值
     *
     * @description

     * @param hKey
     * @param hashKey
     * @return
     */
    <T> T hashGet(String hKey, String hashKey);

    /**
     * 获取所有的散列值
     *
     * @description
     * @param key
     * @return
     */
    Map<Object, Object> hashGetAll(String key);

    /**
     * 哈希表hKey中的域hashKey的值加上增量delta
     *
     * @description 增量可以为负数 如果hKey不存在,会创建一个新的hash并执行HINCRBY命令.
     *              如果hashKey不存在,会在执行前初始化一个0 如果hashKey的值时字符串,会报错
     *              返回返回hKey中hashKey的当前值

     * @param hKey
     * @param hashKey
     * @param delta
     * @return
     */
    Long hashIncrementLongOfHashMap(String hKey, String hashKey, Long delta);

    /**
     * 哈希表hKey中的域hashKey的值加上浮点型增量delta
     *
     * @description

     * @param hKey
     * @param hashKey
     * @param delta
     * @return
     */
    Double hashIncrementDoubleOfHashMap(String hKey, String hashKey, Double delta);

    /**
     * 添加键值对到哈希表key中
     *
     * @description

     * @param key
     * @param hashKey
     * @param value
     */
    void hashPushHashMap(String key, Object hashKey, Object value);

    /**
     * 获取哈希表key中的所有域
     *
     * @description

     * @param key
     * @return
     */
    Set<Object> hashGetAllHashKey(String key);

    /**
     * 获取散列中的字段数量
     *
     * @description

     * @param key
     * @return
     */
    Long hashGetHashMapSize(String key);

    /**
     * 获取哈希中的所有值
     *
     * @description

     * @param key
     * @return
     */
    List<Object> hashGetHashAllValues(String key);

    /**
     * 删除一个或多个哈希字段
     *
     * @description

     * @param key
     * @param hashKeys
     * @return 被成功删除的数量
     */
    Long hashDeleteHashKey(String key, Object... hashKeys);
}
