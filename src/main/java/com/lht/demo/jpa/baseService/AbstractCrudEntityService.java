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

import com.google.common.collect.Lists;
import com.lht.demo.jpa.baseDao.CrudDao;
import com.lht.demo.jpa.baseDao.SimpleJpaCrudDao;
import com.lht.demo.jpa.baseEntity.CrudEntity;
import com.lht.demo.util.IDGenerator;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 通用实体服务类，提供增删改查的默认实现
 *
 * @author
 */
@Transactional(rollbackFor = Throwable.class)
public abstract class AbstractCrudEntityService<E extends CrudEntity<PK>, PK extends Serializable>
   implements CrudService<E, PK> {


    protected CrudDao<E, PK> crudDao;

    public AbstractCrudEntityService() {
    }



    public CrudDao<E, PK> getDao() {
        return this.crudDao;
    }



    /**
     * 获取ID生成器,在insert的时候，如果ID为空,则调用生成器进行生成
     *
     * @return IDGenerator
     */
    protected IDGenerator<PK> getIDGenerator() {
        return IDGenerator.AUTO;
    }


    /**
     *
     * @param pk 主键
     * @return
     */
    @Override
    public int delete(PK pk) {
        Assert.notNull(pk, "parameter can not be null");
        return (int)getDao().delete(pk);
    }

    @Autowired
    public void setCrudDao(CrudDao<E, PK> crudDao) {
        this.crudDao = crudDao;
    }





    @Override
    public List<E> getAll(){
        return getDao().findALl();
    }

    /**
     * 查询结果总数
     *
     * @return 结果总数
     */
    public long count(){
        return getDao().findALl().size();
    }




    @Override
    public PK update(PK pk, E entity) {
        Assert.notNull(pk, "primary key can not be null");
        Assert.notNull(entity, "baseEntity can not be null");
        entity.setId(pk);
        return getDao().update(entity);

    }

    @Override
    public PK update(E entity) {
        return update(entity.getId(), entity);
    }


    @Override
    public PK merge(E entity) {
        if (null != entity.getId() && null != get(entity.getId())) {
            update(entity);
        } else {
            insert(entity);
        }
        return entity.getId();
    }

    @Override
    public List<PK> merge(Collection<E> entities) {
        if (CollectionUtils.isEmpty(entities)) {
            return Lists.newArrayList();
        }
        return entities.stream().map(this::merge).collect(Collectors.toList());
    }

    /**
     * querydsl集成后使用
     * @param id 主键集合
     * @return
     */
    @Override
    public List<E> get(List<PK> id) {
        if (id == null || id.isEmpty()) {
            return new ArrayList<>();
        }
        return null;
    }


    @Override
    public PK insert(E entity) {
        return getDao().insert(entity);
    }




    @Override
    @Transactional(readOnly = true)
    public E get(PK pk) {
        return getDao().findOne(pk);
    }






}
