/*
 *
 *  * Copyright 2016 http://www.kedacomframework.org
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *     http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

package com.lht.demo.jpa.baseDao;

import java.util.List;

/**
 * 通用数据插入DAO接口
 *

 */
public interface InsertDao<E, PK> extends Dao {

    PK insert(E e);

    <S extends E> List<PK> insert(Iterable<S> entities);

    /**
     * 使用nativeSQL 插入数据. 不会更新hibernate session级别缓存.
     * @param entities
     * @param <S>
     * @return
     */
//    <S extends E> List<S> insertSilently(Iterable<S> entities);

}
