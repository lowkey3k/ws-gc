package com.lht.demo.util;

import cn.hutool.core.util.RandomUtil;

/**
 * ID生成器,用于生成ID
 *
 * @author lihaitao
 * @since 3.0
 */
public interface IDGenerator<T> {

    IDGenerator AUTO = () -> null;
    IDGenerator<String> UUID = RandomUtil::simpleUUID;

    T generate();
}

