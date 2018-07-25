package com.lht.demo.jpa.baseDao;

import com.lht.demo.jpa.baseEntity.CrudEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
@NoRepositoryBean
public  interface JpaCrudDao<E extends CrudEntity<PK>, PK extends Serializable> extends CrudDao<E,PK>,JpaSpecificationExecutor<E>, JpaRepository<E, PK>,QuerydslPredicateExecutor<E>
{


}
