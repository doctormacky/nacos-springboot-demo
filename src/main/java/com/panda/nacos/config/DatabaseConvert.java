package com.panda.nacos.config;

import com.alibaba.fastjson2.JSON;
import com.alibaba.nacos.api.config.convert.NacosConfigConverter;

public class DatabaseConvert implements NacosConfigConverter<DatabaseInfo> {

    @Override
    public boolean canConvert(Class<DatabaseInfo> targetType) {
        return true;
    }

    @Override
    public DatabaseInfo convert(String config) {
        return JSON.parseObject(config, DatabaseInfo.class);
    }
}
