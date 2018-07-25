package com.lht.demo.test;

import java.util.ArrayList;
import java.util.List;

public abstract class Parent {

    public abstract void getString();

    private List<String> list;


    public List<String> index(){
        list=new ArrayList<>();
        list.add("list");
        return list;
    }


    public void out(){
       System.out.println( index().get(0));
        getString();
    }

}
