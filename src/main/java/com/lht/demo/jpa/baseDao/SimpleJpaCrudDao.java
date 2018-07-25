package com.lht.demo.jpa.baseDao;

import com.google.common.collect.Lists;
import com.lht.demo.jpa.baseEntity.CrudEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.Getter;
import org.apache.commons.lang3.Validate;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.QuerydslJpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;


@NoRepositoryBean
public class SimpleJpaCrudDao<E extends CrudEntity<PK>, PK extends Serializable> extends QuerydslJpaRepository<E,PK> implements JpaCrudDao<E, PK> {

    @Getter
    private final Class<E> entityClass;
    protected final EntityManager em;//EntityManager 是用来对实体Bean 进行操作的辅助类。他可以用来产生/删除持久化的实体Bean,跟踪实体状态，同步到数据库

    private JPAQueryFactory queryFactory;

    public SimpleJpaCrudDao(JpaEntityInformation<E, PK> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.em=entityManager;
        this.queryFactory = new JPAQueryFactory(entityManager);
        this.entityClass=entityInformation.getJavaType();
    }

    @Override
    public PK insert(E e) {
        Validate.notNull(e);
        E entity = save(e);
        return entity.getId();
    }

    @Override
    public <S extends E> List<PK> insert(Iterable<S> entities) {
        List<PK> result = Lists.newArrayList();
        if (entities == null) {
            return result;
        }
        int i = 0;
        for (S s : entities) {
            i++;
            result.add(insert(s));
            if (i % 50 == 0) {
                em.flush();
                em.clear();
            }
        }
        return result;
    }


   /* protected Path getPropertyPath(Root root, String propertyName) {
        if (StringUtils.contains(propertyName, ".")) {
            List<String> propertyNames = Splitter.on(".").splitToList(propertyName);

            Join join = null;
            for (int i = 0; i < propertyNames.size() - 1; i++) {
                String joinedPropertyName = propertyNames.get(i);
                if (join == null) {
                    join = root.join(joinedPropertyName);
                } else {
                    join = join.join(joinedPropertyName);
                }
            }
            return join.get(propertyNames.get(propertyNames.size() - 1));

        } else {
            return root.get(propertyName);
        }
    }
*/
    /**
     * 按属性条件参数创建Predicate,辅助函数.
     */
    /*@Transactional(readOnly = true)
    public List<E> selectByHql(String hql, Map<String, ?> params) {
        TypedQuery<E> typedQuery = em.createQuery(hql, getEntityClass());
        if (MapUtils.isNotEmpty(params)) {
            for (Map.Entry<String, ?> entry : params.entrySet()) {
                typedQuery.setParameter(entry.getKey(), entry.getValue());
            }
        }
        return typedQuery.getResultList();
    }*/


    @Override
    public <S extends E> List<PK> patch(Iterable<S> entities) {
        List<PK> result = Lists.newArrayList();

        if (entities == null) {
            return result;
        }
        int i = 0;
        for (S s : entities) {
            i++;
            result.add(patch(s));
            if (i % 50 == 0) {
                em.flush();
                em.clear();
            }
        }
        return result;
    }


    @Override
    public PK update(E e) {
        return insert(e);
    }

    @Override
    public <S extends E> List<PK> update(Iterable<S> entities) {
        return insert(entities);
    }

    @Override
    public PK upsert(E e) {
        return insert(e);
    }

    @Override
    public <S extends E> List<PK> upsert(Iterable<S> entities) {
        return insert(entities);
    }

    @Override
    public PK patch(E e) {
       /* E oldEntity = findOne(e.getId());

        E target = BeanMapper.deepMap(oldEntity, getEntityClass());
        com.kedacom.ctsp.web.baseDao.jpa.util.BeanMapper.deepPatch(e, target);*/
        return null;
    }


    @Override
    public long delete(PK pk) {
        deleteById(pk);
        return (Long) pk;
    }

    @Override
    public void delete(Iterable<? extends E> entities) {
        deleteAll(entities);
    }

    @Override
    public E findOne(PK pk) {
        E e= findById(pk).get();
        return e;
    }

    @Override
    public List<E> findALl() {
        return findAll();
    }
}
