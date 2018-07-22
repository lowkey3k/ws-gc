package com.lht.jpa.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2018/7/22.
 */
@RestController
public class World {
    @RequestMapping("hello")
    String index(){
        return "hello world";
    }
}
