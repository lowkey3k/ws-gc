package com.lht.demo.jpa.baseDao;

import com.lht.demo.jpa.baseEntity.CrudEntity;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import java.io.Serializable;

@NoRepositoryBean
public interface CrudDao<E extends CrudEntity<PK>, PK extends Serializable> extends
        InsertDao<E, PK>,
        UpdateDao<E, PK>,
        DeleteDao<E, PK>,
        QueryDao<E, PK>,
        Repository<E, PK>{
}
