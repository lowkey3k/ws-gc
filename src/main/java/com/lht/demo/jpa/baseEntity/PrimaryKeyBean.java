package com.lht.demo.jpa.baseEntity;

/**
 * 主键Bean
 *
 **/
public interface PrimaryKeyBean<PK> {

    String ID = "id";

    PK getId();

    void setId(PK id);
}
