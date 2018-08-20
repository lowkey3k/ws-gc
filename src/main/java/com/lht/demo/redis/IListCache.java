package com.lht.demo.redis;

import java.util.List;

public interface IListCache {
    /**
     * 从右向左存压栈
     * @description

     * @param key
     * @param value
     */
    void listRightPushList(String key, Object value);

    /**
     * 从右出栈
     * @description

     * @param key
     * @return
     */
    Object listRightPopList(String key);

    /**
     * 从左向右存压栈
     * @description

     * @param key
     * @param value
     */
    void listLeftPushList(String key, Object value);

    /**
     * 从左出栈
     * @description

     * @param key
     * @return
     */
    Object listLeftPopList(String key);

    /**
     * 集合list的长度
     * @description

     * @param key
     * @return
     */
    Long listSize(String key);

    /**
     * 查询列表 key 中指定区间内的元素，区间以偏移量 start 和 stop 指定。
     * @description

     * @param key
     * @param start
     * @param end
     * @return
     */
    List<Object> listRangeList(String key, Long start, Long end);

    /**
     * 移除key中值为value的i个,返回删除的个数；如果没有这个元素则返回0
     * @description

     * @param key
     * @param i
     * @param value
     * @return
     */
    Long listRemoveFromList(String key, long i, Object value);

    /**
     * 根据下标查询list中某个值
     * @description

     * @param key
     * @param index
     * @return
     */
    Object listIndexFromList(String key, long index);

    /**
     * 根据下标设置value
     * @description

     * @param key
     * @param index
     * @param value
     */
    void listSetValueToList(String key, long index, Object value);

    /**
     * 裁剪(删除), 删除 除了[start,end]以外的所有元素
     * @description

     * @param key
     * @param start
     * @param end
     */
    void listTrimByRange(String key, Long start, Long end);
}

