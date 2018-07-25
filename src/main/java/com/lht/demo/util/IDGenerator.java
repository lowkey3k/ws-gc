package com.lht.demo.util;

/**
 * ID生成器,用于生成ID
 *
 * @author xuwei
 * @since 3.0
 */
public interface IDGenerator<T> {

    IDGenerator AUTO = () -> null;
    IDGenerator<String> UUID = RandomUtil::simpleUUID;

    T generate();
}

