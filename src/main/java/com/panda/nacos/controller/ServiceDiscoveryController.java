package com.panda.nacos.controller;

import com.alibaba.fastjson2.JSON;
import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("discovery")
public class ServiceDiscoveryController {
    @NacosInjected
    private NamingService namingService;

    @GetMapping("registerService")
    public void registerService() throws NacosException {
        namingService.registerInstance("userRegister", "13.11.11.11", 8082, "hangzhou");
    }

    @GetMapping("getAllInstances")
    public void getAllInstances() throws NacosException {
        log.info(JSON.toJSONString(namingService.getAllInstances("userRegister")));
    }
}
