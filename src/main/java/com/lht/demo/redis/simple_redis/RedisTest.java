package com.lht.demo.redis.simple_redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPoolConfig;


import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class RedisTest {
    public static void main(String[] args){

           //redis配置
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxActive(10);

            Jedis jedis=new Jedis("localhost");
            System.out.println("连接成功");

    //        System.out.println("服务正在运行"+jedis.ping());
            //redis字符串实例
            jedis.set("key1","value");
            System.out.println("redis存储的字符串为："+jedis.get("key1"));


            //redis List(列表)实例
            jedis.lpush("site-list", "Runoob");
            jedis.lpush("site-list", "Google");
            jedis.lpush("site-list", "Taobao");


            // 获取存储的数据并输出
            List<String> list = jedis.lrange("site-list", 0 ,2);
            for(int i=0; i<list.size(); i++) {
                System.out.println("列表项为: "+list.get(i));

            //Redis Java Keys 实例
                Set<String> keys=jedis.keys("*");
                Iterator<String> it=keys.iterator();
                while (it.hasNext()){
                    String key=it.next();
                    System.out.println("redis存储的key:"+key);
                }
            }


    }

}
