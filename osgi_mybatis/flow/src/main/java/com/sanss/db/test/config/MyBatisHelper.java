package com.sanss.db.test.config;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Map;

import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.apache.log4j.Logger;

public class MyBatisHelper {
    static Logger logger = Logger.getLogger(MyBatisHelper.class);
    public static final String POOLMAXIMUMACTIVECONNECTIONS = "poolMaximumActiveConnections";
    public static final String POOLMAXIMUMIDLECONNECTIONS = "poolMaximumIdleConnections";
    public static final String POOLTIMETOWAIT = "poolTimeToWait";
    public static final String POOLPINGENABLED = "poolPingEnabled";
    public static final String POOLPINGCONNECTIONSNOTUSEDFOR = "poolPingConnectionsNotUsedFor";
    public static final String POOLPINGQUERY = "poolPingQuery";
    public static final String PAGENAME = "pageName";
    public static final String DRIVER = "driver";
    public static final String URL = "url";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";

    /*public static SqlSessionFactory initMybatis() {
        SqlSessionFactory sqlSessionFactory = null;
        try {

            InputStream in = new FileInputStream("classpath:mybatis.xml");

            // 使用SqlSessionFactoryBuilder创建sessionFactory
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);

            logger.info("初始化数据库连接");

        } catch (Exception e) {
            e.printStackTrace();
            logger.info("mybatis初始化错误");
        }
        return sqlSessionFactory;
    }*/

    public static SqlSessionFactory initMybatis() {
        String driver = null;
        String url = null;
        String username = null;
        String password = null;

        Map<String, String> sqlConfig = Config.getSqlConfig();
        driver = sqlConfig.get(DRIVER);
        url = sqlConfig.get(URL);
        username = sqlConfig.get(USERNAME);
        password = sqlConfig.get(PASSWORD);

        PooledDataSource dataSource = new PooledDataSource(driver, url, username, password);
        dataSource.setPoolMaximumActiveConnections(Integer.parseInt(sqlConfig.get(POOLMAXIMUMACTIVECONNECTIONS)));
        dataSource.setPoolMaximumIdleConnections(Integer.parseInt(sqlConfig.get(POOLMAXIMUMIDLECONNECTIONS)));
        dataSource.setPoolTimeToWait(Integer.parseInt(sqlConfig.get(POOLTIMETOWAIT)));
        dataSource.setPoolPingEnabled(Boolean.parseBoolean(sqlConfig.get(POOLPINGENABLED)));
        dataSource.setPoolPingConnectionsNotUsedFor(Integer.parseInt(sqlConfig.get(POOLPINGCONNECTIONSNOTUSEDFOR)));
        dataSource.setPoolPingQuery(sqlConfig.get(POOLPINGQUERY));
        TransactionFactory transactionFactory = new JdbcTransactionFactory();
        Environment environment = new Environment("development", transactionFactory, dataSource);
        Configuration configuration = new Configuration(environment);
        configuration.addMappers(sqlConfig.get(PAGENAME));
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
        logger.info("mybatis初始化成功");
        return sqlSessionFactory;
    }
}
