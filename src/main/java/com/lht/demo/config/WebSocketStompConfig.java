package com.lht.demo.config;

import com.lht.demo.entity.User;
import com.lht.demo.service.UserService;
import com.lht.demo.service.serviceImpl.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

import javax.persistence.EntityManagerFactory;

@Configuration
public class WebSocketStompConfig {
    @Bean
    public ServerEndpointExporter serverEndpointExporter(){

        return new ServerEndpointExporter();
    }

    @Bean
    public EntityManagerFactory entityManagerFactory(EntityManagerFactory entityManagerFactory){
      return entityManagerFactory;
    }

    @Bean
    public UserService userService(){
        return new UserServiceImpl();
    }

}
