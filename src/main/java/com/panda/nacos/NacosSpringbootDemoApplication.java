package com.panda.nacos;

import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;


@EnableConfigurationProperties
@NacosPropertySource(dataId = "db.properties", autoRefreshed = true)
@SpringBootApplication
public class NacosSpringbootDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(NacosSpringbootDemoApplication.class, args);
    }

}
