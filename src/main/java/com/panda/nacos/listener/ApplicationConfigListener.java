package com.panda.nacos.listener;

import com.alibaba.nacos.api.config.annotation.NacosConfigListener;
import com.panda.nacos.config.DatabaseConvert;
import com.panda.nacos.config.DatabaseInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ApplicationConfigListener {

    @NacosConfigListener(dataId = "db-dev.properties", converter = DatabaseConvert.class)
    public void listener(DatabaseInfo databaseInfo) {
        log.info("-----------------------------------------");
        log.info("databaseInfo : {}", databaseInfo);
    }

    @NacosConfigListener(dataId = "db-dev.properties")
    public void listener(String content) {
        log.info("***************************************");
        log.info(content);
    }
}
