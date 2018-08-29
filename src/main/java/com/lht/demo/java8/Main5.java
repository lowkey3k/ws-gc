package com.lht.demo.java8;

import cn.hutool.core.util.RandomUtil;
import com.lht.demo.util.IDGenerator;

import java.time.Clock;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class Main5 {
    public static void main(String[] args) {
        long[] arrayOfLong = new long [ 200 ];

        //产生随机数组
        Arrays.parallelSetAll( arrayOfLong,
                index -> ThreadLocalRandom.current().nextInt( 1000000 ) );
        //输出十个
        Arrays.stream( arrayOfLong ).limit( 10 ).forEach(
                i -> System.out.print( i + " " ) );
        System.out.println();

        //排序
        Arrays.parallelSort( arrayOfLong );
        Arrays.stream( arrayOfLong ).limit( 10 ).forEach(
                i -> System.out.print( i + " " ) );
        System.out.println();

        /**
         * 下面的代码相当于
         * int n=5;
         * int sum=0;
         *  //传统的for 循环 时间大概 3毫秒
         * for(int i=1;i<=n;i++){
         *   sum+=i;
         * }
         */

        Clock clock=Clock.systemUTC();
        LocalTime localTime=LocalTime.now(clock);
        int n=5;
        //耗时大约128毫秒
        Long sum= Stream.iterate(1L,i->i+1).limit(n).reduce(Long::sum).get();
        //耗时大约70毫秒左右 原因在于 装箱问题和iterate 无法进行分流的操作
        Long sum1=Stream.iterate(1L, i -> i + 1).limit(n).parallel().reduce(Long::sum).get();
        //耗时大约两毫秒
        Long sum2= LongStream.rangeClosed(1,n).parallel().reduce(Long::sum).getAsLong();
        System.out.println(RandomUtil.simpleUUID()+"......");
        System.out.println(IDGenerator.UUID.generate()+">>>>>");

    }

}
