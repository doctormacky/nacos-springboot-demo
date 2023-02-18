package com.panda.nacos.config;

import lombok.Data;

@Data
public class DatabaseInfo {

    private String username;

    private String password;

    private String url;

    private String type;

    private String driver;
}
