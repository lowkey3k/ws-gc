package com.lht.demo.jpa.baseDao;

import java.util.List;

/**
 * 更新Dao
 *

 **/
public interface UpdateDao<E, PK> extends Dao {

    /**
     * 全量字段更新
     *
     * @param e
     * @return
     */
    PK update(E e);

    /**
     * 全量字段更新(批量)
     *
     * @param entities
     * @return
     */
    <S extends E> List<PK> update(Iterable<S> entities);

    PK upsert(E e);

    <S extends E> List<PK> upsert(Iterable<S> entities);

    /**
     * 部分字段更新
     *
     * @param e
     * @return
     */
    PK patch(E e);

    /**
     * 部分字段更新(批量)
     *
     * @param entities
     * @return
     */
    <S extends E> List<PK> patch(Iterable<S> entities);
}
