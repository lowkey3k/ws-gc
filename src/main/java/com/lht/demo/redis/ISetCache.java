package com.lht.demo.redis;
import java.util.Set;

public interface ISetCache {

    /**
     * 将一个或多个 value 元素加入到集合 key 当中，已经存在于集合的 value 元素将被忽略。
     *
     * @description

     * @param key
     * @param values
     * @return 被添加到集合中的新元素的数量，不包括被忽略的元素
     */
    Long setAddSetMap(String key, Object... values);

    /**
     * 获取set集合的大小
     *
     * @description

     * @param key
     * @return
     */
    Long setGetSizeForSetMap(String key);

    /**
     * 获取set集合中的元素
     *
     * @description

     * @param key
     * @return
     */
    Set<Object> setGetMemberOfSetMap(String key);

    /**
     * 检查元素是不是set集合中的
     *  @param key
     * @param o
     * @return
     */
    boolean setCheckIsMemberOfSet(String key, Object o);
}
