package com.panda.nacos.controller;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("config")
public class ConfigController {
    @NacosValue(value = "${username}", autoRefreshed = true)
    private String username;

    @NacosValue(value = "${password}", autoRefreshed = true)
    private String password;

    @GetMapping("getUsernameAndPassword")
    public void getUsernameAndPassword() {
        log.info("username = {} , password = {}", username, password);
    }
}
