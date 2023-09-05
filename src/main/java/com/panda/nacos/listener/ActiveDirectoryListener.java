package com.panda.nacos.listener;

import java.util.concurrent.TimeUnit;

import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.nacos.api.config.annotation.NacosConfigListener;
import com.panda.nacos.config.ActiveDirectoryDataSource;
import com.panda.nacos.config.ActiveDirectoryProperties;

import lombok.extern.slf4j.Slf4j;

/**  
 * @Title: ActiveDirectoryListener.java
 *
 * @Description: listener
 *
 * @author Macky liuyunsh@cn.ibm.com
 *
 * @Copyright: 2022-2099 IBM All rights reserved.
 *
 * @date 2023-09-05 02:14:27 
 */
@Slf4j
@Component
public class ActiveDirectoryListener {
	
	private final ActiveDirectoryDataSource dynamicDataSource;

    private final ActiveDirectoryProperties dataSourceProperties;

    public ActiveDirectoryListener(ActiveDirectoryDataSource dynamicDataSource,
    		ActiveDirectoryProperties dataSourceProperties) {
        this.dynamicDataSource = dynamicDataSource;
        this.dataSourceProperties = dataSourceProperties;
    }
    
    @Async
    @NacosConfigListener(dataId = "adconfig", groupId = "DEFAULT_GROUP")
    public void dataSourceUpdatedListener(String config) {
        log.info("配置更新事件 config:{}", config);

        try {
            // 等待5s保证配置已刷新完成
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException ignored) {
        }
        log.info("数据源配置变更：{}", JSON.toJSONString(dataSourceProperties, true));

        // 构建新数据源
        LdapContextSource dataSource = dataSourceProperties.initLdapContextSource();
        log.info("构建新数据源 datasource: {}", dataSource);

        LdapContextSource oldDataSource = dynamicDataSource.getAndSet(dataSource);

        log.info("关闭旧数据源 datasource: {}", oldDataSource);
        //oldDataSource.close();
    }

}
