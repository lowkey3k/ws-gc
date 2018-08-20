package com.lht.demo.redis;


import java.util.Collection;

/**
 * author:LiHaitao
 * 封装redisTemplate公用处理接口
 */
public interface ICache extends IStringCache,ISetCache,IListCache,IHashCache {
    /**
     * 删除键为key的缓存(hash/set/list/string)
     * @description

     * @param key
     */
    void deleteFromRedis(String key);

    /**
     * 删除键为key的缓存(hash/set/list/string)
     * @description
     * @param keys
     */
    void deleteFromRedis(Collection<String> keys);
}
