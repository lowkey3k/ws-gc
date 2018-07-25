package com.lht.demo.web.entity;

import com.lht.demo.jpa.baseEntity.CrudEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Data
@Entity
@Table(name = "user")
public class User implements CrudEntity<Long> {

    @Id
    private Long id;

    private String name;


    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {

    }


}
