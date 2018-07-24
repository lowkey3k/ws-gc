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

package com.lht.demo.jpa.baseService;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author xuwei
 */
public interface InsertService<E, PK> {

    /**
     * 添加一条数据
     *
     * @param data 要添加的数据
     * @return 添加后生成的主键
     */
    PK insert(E data);

    default List<PK> insert(Collection<E> entities) {
        return entities.stream().map(this::insert).collect(Collectors.toList());
    }

}
