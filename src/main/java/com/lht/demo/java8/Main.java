package com.lht.demo.java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class Main {

    public static void  printValur(String str){
        System.out.println("print value : "+str);
    }

    public static void main(String[] args) {

        Collection collection=new ArrayList<>();
        List list=new ArrayList();

        List<String> al = Arrays.asList("a","b","c","d");
        for (String a: al) {
            Main.printValur(a);
        }
        //下面的for each循环和上面的循环是等价的
        al.forEach(x->{
            Main.printValur(x);
        });
    }
}
