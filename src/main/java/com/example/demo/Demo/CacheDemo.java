package com.example.demo.Demo;


import com.google.common.base.Preconditions;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.Weigher;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author lihaitao
 * @email  984695425@qq.com
 * @description 本地缓存工具:
 *
 * 这是一个单独的使用Guava Cache本地缓存工具类
 *
 */

/**
 * 方法介绍：
 * expireAfterAccess: 当缓存项在指定的时间段内没有被读或写就会被回收。
   expireAfterWrite：当缓存项在指定的时间段内没有更新就会被回收。
   refreshAfterWrite：当缓存项上一次更新操作之后的多久会被刷新。

   asMap视图
   asMap视图提供了缓存的ConcurrentMap形式，但asMap视图与缓存的交互需要注意：
   cache.asMap()包含当前所有加载到缓存的项。因此相应地，cache.asMap().keySet()包含当前所有已加载键;
   asMap().get(key)实质上等同于cache.getIfPresent(key)，而且不会引起缓存项的加载。这和Map的语义约定一致。


     1）V get(K k): 内部调用getOrLoad(K key)方法，缓存中有对应的值则返回，没有则使用CacheLoader load方法
     getOrLoad(K key)方法为线程安全方法，内部加锁
     2)V getIfPresent(Object key):缓存中有对应的值则返回，没有则返回NULL
     3)ImmutableMap getAll(Iterable keys) ：提供一组keys筛选出符合条件的所有值。内部调用遍历keys调用get（K key)方法获得已经缓存的对象，
       没有缓存的对象则通过调用CacheLoader.loadAll方法加载，如果没实现loadAll方法则会抛出UnsupportedLoadingOperationException异常，
       处理这个异常最终会遍历每个key通过lockedGetOrLoad(key, hash, loader)方法调用CacheLoader.load方法,实现加载成功
     4）ImmutableMap getAll(Iterable keys)： 提供一组keys筛选出符合条件缓存中存在的所有值
     5) long size() : 缓存对象数量
     6)put(K key,V value): 直接显示地向缓存中插入值，这会直接覆盖掉已有键之前映射的值。
     7)invalidate(Object key)：显式地清除指定key的缓存对象
     8) invalidateAll(Iterable keys) : 清除批量缓存对象
     9)invalidateAll(): 清除所有缓存对象
    10) public void refresh(K key) :刷新指定key的缓存对象,刷新和回收不太一样。刷新表示为键加载新值，这个过程可以是异步的。在刷新操作进行时，缓存仍然可以向其他线程返回旧值，而不像回收操作，读缓存的线程必须等待新值加载完成。如果刷新过程抛出异常，缓存将保留旧值，而异常会在记录到日志后被丢弃[swallowed]。重载CacheLoader.reload可以扩展刷新时的行为，这个方法允许开发者在计算新值时使用旧的值

    11)ConcurrentMap asMap():获取缓存数据转换成Map类型
 * @param <K>
 * @param <V>
 */

public class CacheDemo<K, V> {

    /**
     * 缓存对象
     */
    private LoadingCache<K, V> cache;

    /**
     * 默认缓存最大容量
     */
    public static final int DEFAULT_MAXIMUM_SIZE = 16;

    /**
     * 默认刷新时间
     */
    public static final int DEFAULT_REFRESH_TIME = 1;

    /**
     * 默认过期时间
     */
    public static final int DEFAULT_EXPIRE_TIME = 1;

    /**
     * 基于权重的回收中的默认最大权重
     */
    public static final int DEFAULT_MAXIMUM_WEIGHT = 1000;

    /**
     * 默认时间单位：秒
     */
    public static final TimeUnit DEFAULT_TIME_UNIT = TimeUnit.SECONDS;

    /**
     * 默认过期方式：不自动过期
     */
    public static final ExpireMethod DEFAULT_EXPIRE_METHOD = ExpireMethod.NO_EXPIRE;

    /**
     * 默认引用方式：强引用
     */
    public static final ReferenceMethod DEFAULT_REFERENCE_METHOD = ReferenceMethod.DEFAULT;

    public CacheDemo(CacheLoader<K, V> cacheLoader, Weigher<K, V> weigher, RemovalListener<K, V> removalListener,
                       long maximumWeight, int maximumSize, long refreshTime, TimeUnit refreshTimeUnit, long expireTime,
                       TimeUnit expireTimeUnit, ExpireMethod expireMethod, ReferenceMethod keyReferenceMethod,
                       ReferenceMethod valueReferenceMethod) {

        Preconditions.checkNotNull(cacheLoader, "cache loader must not be null");

        maximumSize = maximumSize <= 0 ? DEFAULT_MAXIMUM_SIZE : maximumSize;
        refreshTime = refreshTime < 0 ? DEFAULT_REFRESH_TIME : refreshTime;
        expireTime = expireTime < 0 ? DEFAULT_EXPIRE_TIME : expireTime;
        maximumWeight = maximumWeight <= 0 ? DEFAULT_MAXIMUM_WEIGHT : maximumWeight;
        refreshTimeUnit = refreshTimeUnit == null ? DEFAULT_TIME_UNIT : refreshTimeUnit;
        expireTimeUnit = expireTimeUnit == null ? DEFAULT_TIME_UNIT : expireTimeUnit;
        expireMethod = expireMethod == null ? DEFAULT_EXPIRE_METHOD : expireMethod;
        keyReferenceMethod = (keyReferenceMethod == null || keyReferenceMethod == ReferenceMethod.SOFT)
                ? DEFAULT_REFERENCE_METHOD : keyReferenceMethod;
        valueReferenceMethod = valueReferenceMethod == null ? DEFAULT_REFERENCE_METHOD : valueReferenceMethod;
        //初始化本地缓存
        init(cacheLoader, weigher, removalListener, maximumWeight, maximumSize, refreshTime, refreshTimeUnit,
                expireTime, expireTimeUnit, expireMethod, keyReferenceMethod, valueReferenceMethod);
    }

    public CacheDemo() {
    }

    /**
     * 初始化本地缓存
     */
    public void init(CacheLoader<K, V> cacheLoader, Weigher<K, V> weigher, RemovalListener<K, V> removalListener,
                     long maximumWeight, long maximumSize, long refreshTime, TimeUnit refreshTimeUnit, long expireTime,
                     TimeUnit expireTimeUnit, ExpireMethod expireMethod, ReferenceMethod keyReferenceMethod,
                     ReferenceMethod valueReferenceMethod) {
        CacheBuilder cacheBuilder = CacheBuilder.newBuilder();
        cacheBuilder = weigher != null ? cacheBuilder.weigher(weigher).maximumWeight(maximumWeight) : cacheBuilder;
        cacheBuilder = removalListener != null ? cacheBuilder.removalListener(removalListener) : cacheBuilder;
        cacheBuilder = cacheBuilder.maximumSize(maximumSize).refreshAfterWrite(refreshTime, refreshTimeUnit);
        cacheBuilder = expireMethod == ExpireMethod.NO_EXPIRE ? cacheBuilder
                : (expireMethod == ExpireMethod.AFTER_WRITE ? cacheBuilder.expireAfterWrite(expireTime, expireTimeUnit)
                : cacheBuilder.expireAfterAccess(expireTime, expireTimeUnit));
        cacheBuilder = keyReferenceMethod == ReferenceMethod.DEFAULT ? cacheBuilder : cacheBuilder.weakKeys();
        cacheBuilder = valueReferenceMethod == ReferenceMethod.DEFAULT ? cacheBuilder
                : (valueReferenceMethod == ReferenceMethod.SOFT ? cacheBuilder.softValues()
                : cacheBuilder.weakValues());
        cache = cacheBuilder.build(cacheLoader);
    }

    /**
     * 添加一个键值对
     *
     * @param key
     */
    public void addOne(K key) {
        cache.refresh(key);
    }

    /**
     * 添加多个键值对
     *
     * @param keys
     */
    public void addAll(Iterable<K> keys) {
        for (K key : keys) {
            cache.refresh(key);
        }
    }

    /**
     * 删除一个键值对
     *
     * @param key
     * @throws ExecutionException
     * @return 被删除的键值对的值
     */
    public V deleteOne(K key) throws ExecutionException {
        V value = getOne(key);
        cache.invalidate(key);
        return value;
    }

    /**
     * 删除多个键值对
     *
     * @param keys
     */
    public void deleteAll(Iterable<K> keys) {
        cache.invalidateAll(keys);
    }

    /**
     * 清空缓存
     */
    public void deleteAll() {
        cache.invalidateAll();
    }

    /**
     * 得到某个键对应的值
     *
     * @param key
     * @throws ExecutionException
     * @return
     */
    public V getOne(K key) throws ExecutionException {
        return cache.get(key);
    }

    /**
     * 判断某个键值对是否存在
     *
     * @param key
     * @return
     */
    public boolean exists(K key) {
        return cache.asMap().containsKey(key);
    }

    /**
     * 手动像缓存里添加一个键值对
     *
     * @param key
     * @param value
     */
    public void put(K key, V value) {
        cache.put(key, value);
    }

    @Override
    public String toString() {
        return cache.asMap().toString();
    }

    /**
     * 自动回收方式
     */
    public enum ExpireMethod {
        // 不自动回收
        NO_EXPIRE,

        // 缓存项在给定时间内没有被写访问（创建或覆盖），则回收
        AFTER_WRITE,

        // 缓存项在给定时间内没有被读/写访问，则回收
        AFTER_ACCESS
    }

    /**
     * 键值对象引用方式
     */
    public enum ReferenceMethod {
        // 默认强引用方式，不会被垃圾回收
        DEFAULT,

        // 弱引用方式，会被垃圾回收
        WEAK,

        // 软引用方式，在内存很多的时候会被垃圾回收
        SOFT
    }
}


