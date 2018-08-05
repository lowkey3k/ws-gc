package com.lht.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;


@SpringBootApplication(exclude = DataSourceAutoConfiguration.class,scanBasePackages = "com.lht.demo")
public class Application {

    protected final static Logger logger = LoggerFactory.getLogger(Application.class);


    public static void main(String[] args) {

        SpringApplication app = new SpringApplication(Application.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
        logger.info("PortalApplication is success!");
        System.err.println("Started successfully!");

    }

}