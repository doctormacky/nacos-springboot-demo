package com.panda.nacos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import com.alibaba.nacos.spring.context.annotation.discovery.EnableNacosDiscovery;


@SpringBootApplication
@NacosPropertySource(dataId = "db-dev.properties", autoRefreshed = true)
public class NacosSpringbootDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(NacosSpringbootDemoApplication.class, args);
    }

}
