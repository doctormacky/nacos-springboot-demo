package com.panda.nacos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;


@EnableConfigurationProperties
@SpringBootApplication
public class NacosSpringbootDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(NacosSpringbootDemoApplication.class, args);
    }

}
