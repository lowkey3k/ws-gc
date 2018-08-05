package com.lht.demo.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable{

    private String name;

    private Integer age;

    @Override
    public String toString(){
        return "姓名"+name+"年龄"+age;
    }
}
