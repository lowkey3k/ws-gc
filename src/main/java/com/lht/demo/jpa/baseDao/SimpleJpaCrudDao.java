package com.lht.demo.jpa.baseDao;

import com.google.common.collect.Lists;
import com.lht.demo.jpa.baseEntity.CrudEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.apache.commons.lang3.Validate;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.QuerydslJpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.Iterator;
import java.util.List;


@NoRepositoryBean
public class SimpleJpaCrudDao<E extends CrudEntity<PK>, PK extends Serializable> extends QuerydslJpaRepository<E,PK> implements JpaCrudDao<E, PK> {
    private final Class<E> entityClass;
    protected final EntityManager em;
    private JPAQueryFactory queryFactory;


    public SimpleJpaCrudDao(JpaEntityInformation<E, PK> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityClass = entityInformation.getJavaType();
        this.queryFactory = new JPAQueryFactory(entityManager);
        this.em = entityManager;
    }

    @Override
    public PK insert(E e) {
        Validate.notNull(e);
        E entity = save(e);
        return entity.getId();
    }



    @Override
    public void delete(E e){
        delete(e.getId());
    }

    @Override
    public PK update(E e) {
        return insert(e);
    }



    @Override
    public PK upsert(E e) {
        return insert(e);
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
