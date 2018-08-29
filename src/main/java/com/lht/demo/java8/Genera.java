package com.lht.demo.java8;

/**
 * @Description Genera:
 * @Author LiHaitao
 * @Date 2018/8/28 16:19
 * @UpdateUser
 * @UpdateDescrip
 * @UpdateDate
 * @Version 1.0.0
 **/
public interface Genera<T> {

    Genera<String> UUID=()-> System.out.println("nihao");

    void generater();

}
