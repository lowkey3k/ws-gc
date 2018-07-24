

package com.lht.demo.jpa.baseService;

import java.util.Collection;
import java.util.List;

public interface UpdateService<E, PK> {
    /**
     * 修改记录信息
     *
     * @param data 要修改的对象
     * @return 影响记录数
     */
    PK update(PK id, E data);

    PK update(E data);

    /**
     * 批量修改记录
     *
     * @param data 要修改的记录集合
     * @return 影响记录数
     */
    default int update(Collection<E> data) {
        return Long.valueOf(data.stream().map(this::update).count()).intValue();
    }

    /**
     * 保存或修改
     *
     * @param entity 要修改的数据
     * @return 主键
     */
    PK merge(E entity);


    /**
     * 保存或修改
     *
     * @param entities 要修改的数据
     * @return 主键列表
     */
    List<PK> merge(Collection<E> entities);


//    PK patch(PK id, E source);
}
