package com.sanss.db.test.mybatis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SqlMapConfig {
    private final static Logger log = LoggerFactory.getLogger(SqlMapConfig.class);

    private String driver;
    private String url;
    private String username;
    private String password;
    private String poolMaximumIdleConnections;
    private String poolMaximumActiveConnections;
    private String mybatisPath;

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPoolMaximumIdleConnections() {
        return poolMaximumIdleConnections;
    }

    public void setPoolMaximumIdleConnections(String poolMaximumIdleConnections) {
        this.poolMaximumIdleConnections = poolMaximumIdleConnections;
    }

    public String getPoolMaximumActiveConnections() {
        return poolMaximumActiveConnections;
    }

    public void setPoolMaximumActiveConnections(String poolMaximumActiveConnections) {
        this.poolMaximumActiveConnections = poolMaximumActiveConnections;
    }

    public String getMybatisPath() {
        return mybatisPath;
    }

    public void setMybatisPath(String mybatisPath) {
        this.mybatisPath = mybatisPath;
    }
}
