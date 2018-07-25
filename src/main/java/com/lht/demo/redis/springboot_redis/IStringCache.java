package com.lht.demo.redis.springboot_redis;

public interface IStringCache {

    /**
     * 如果key已经存在且是一个字符串,APPEND命令将value追加到原来的值的末尾. 如果key不存在,APPEND就简单的将给定key设置为value
     *
     * @param key
     * @param value
     * @return 追加value后, 字符串的长度
     * @description

     */
    Integer stringAppendString(String key, String value);

    /**
     * 获取指定键的值
     *
     * @param key
     * @return
     * @description

     */
    String stringGetStringByKey(String key);

    /**
     * 获取存储在键上的字符串的子字符串
     *
     * @param key
     * @param start
     * @param end
     * @return 截取后的字符串
     * @description

     */
    String stringGetSubStringFromString(String key, long start, long end);

    /**
     * 将键的整数值按给定的长整型数值增加
     *
     * @param key
     * @param delta
     * @return 增长后的结果集
     * @description

     */
    Long stringIncrementLongString(String key, Long delta);

    /**
     * 键的整数值按给定的浮点型数值增加
     *
     * @param key
     * @param delta
     * @return 增长后的结果集
     * @description

     */
    Double stringIncrementDoubleString(String key, Double delta);

    /**
     * 设置指定键的值
     *
     * @param key
     * @param value
     * @description

     */
    void stringSetString(String key, String value);

    /**
     * 设置键的字符串值并返回其旧值
     *
     * @param key
     * @param value
     * @return
     * @description

     */
    String stringGetAndSet(String key, String value);

    /**
     * 使用键和到期时间来设置值,时间单位默认为毫秒
     *
     * @param key
     * @param value
     * @param timeout
     * @description

     */
    void stringSetValueAndExpireTime(String key, String value, long timeout);

}