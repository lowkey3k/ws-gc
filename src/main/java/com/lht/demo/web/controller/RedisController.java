package com.lht.demo.web.controller;

import com.lht.demo.redis.springboot_redis.redisImpl.CacheImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/redis")
public class RedisController {

    private CacheImpl cache=new CacheImpl();

    @RequestMapping(value = "getName",method = RequestMethod.GET)
    public String getName(@RequestParam String name){
        System.out.println(name);
        return cache.stringGetStringByKey(name);
    }

    @RequestMapping("/add")
    public void redisAddName(@RequestParam String name){
        System.out.println(name);
        cache.stringSetValueAndExpireTime("name",name,20*1000);
    }

}
