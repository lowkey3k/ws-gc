package com.lht.demo.controller;

import com.lht.demo.entity.User;
import com.lht.demo.redis.ICache;
import com.lht.demo.util.IDGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private ICache cache;

    private IDGenerator idGenerator;
    @RequestMapping("/set")
    public String getUser(){


        User user=new User();
        user.setName("lihaitao");
        user.setAge(18);
       /* String ustr=JSONObject.toJSONString(user);
        cache.hashPushHashMap("user",user.getName(),ustr);
        String user1=cache.hashGet("user",user.getName());
        User user2=JSONObject.parseObject(user1,User.class);
        System.err.println(user2);*/
        cache.listLeftPushList("lihaitao",user);
      User user1=(User)  cache.listRightPopList("lihaitao");
       User user3=(User) cache.listLeftPopList("lihaitao");
        System.err.println(user3+"ssssssssssssssssssssss"+user1);
        return "ok";
    }
}
