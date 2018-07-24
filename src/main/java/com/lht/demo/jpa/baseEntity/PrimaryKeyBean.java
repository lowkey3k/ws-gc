package com.lht.demo.jpa.baseEntity;

/**
 * 主键Bean
 *
 * @author xuwei
 * @create 2018-03-01 09:44
 **/
public interface PrimaryKeyBean<PK> {

    String ID = "id";

    PK getId();

    void setId(PK id);
}
