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


import org.apache.commons.collections4.CollectionUtils;

import java.util.Collection;

/**
 * @author
 */
public interface DeleteService<PK> {
    /**
     * 根据主键删除记录
     *
     * @param pk 主键
     * @return 影响记录数
     */
    int delete(PK pk);

    /**
     * 批量删除记录
     *
     * @param ids 主键
     * @return 影响记录数
     */
    default int delete(Collection<PK> ids) {
        if (CollectionUtils.isNotEmpty(ids)) {
            ids.stream().map(this::delete);
        }
        return CollectionUtils.size(ids);
    }



}
