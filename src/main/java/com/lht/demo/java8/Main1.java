package com.lht.demo.java8;

import cn.hutool.core.util.RandomUtil;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class Main1 {

    public static void  printValur(String str){
        System.out.println("print value : "+str);
    }

    public static void main(String[] args) {
        List<String> al = Arrays.asList("a", "c", "b", "d");
        al.forEach(Main::printValur);
        //下面的方法和上面等价的
        Consumer<String> methodParam = Main::printValur; //方法参数
        al.forEach(x -> methodParam.accept(x));//方法执行accept

        Function<String,String> ss=s -> {
            return RandomUtil.simpleUUID()+s;
        };
        String s1=ss.apply("ss");
        System.out.println(s1+"<--------------");
        //排序
        al.sort( ( e1, e2 ) -> e1.compareTo( e2 ));
        System.out.println(al.toString());
        //排序等价于上面这种写法
        al.sort( ( e1, e2 ) -> {
            int result = e1.compareTo( e2 );
            return result;
        } );

        int sum=1000_0;
        int binary=0b1001_1001;

        System.out.println(binary+"binary");
        System.out.println(al.toString());
    }
}
