package com.lht.demo.config;

import com.lht.demo.jpa.baseDao.CrudDao;
import com.lht.demo.redis.springboot_redis.redisImpl.CacheImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

@Configuration
public class WebSocketStompConfig {
    @Bean
    public ServerEndpointExporter serverEndpointExporter(){

        return new ServerEndpointExporter();
    }
    @Bean
    public CacheImpl cache(){
        return new CacheImpl();
    }
    @Bean
    public CrudDao getCrudDao(){
  return  null;
    }
}
