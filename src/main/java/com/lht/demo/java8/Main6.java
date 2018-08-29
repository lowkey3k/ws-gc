package com.lht.demo.java8;

import java.util.function.Consumer;
import java.util.function.Function;

public class Main6 {

    public static void main(String[] args) {
        Consumer<Integer> consumer=System.out::println;
        consumer.accept(100);


        Function<Integer,Integer> function=x->{
            System.out.println(x);
            return x;
        };
        function.apply(101);

        Genera.UUID.generater();
    }
}
