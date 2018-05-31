package com.sanss.db.test.mybatis;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class MybatisTemplate {
    private final static Logger log = LoggerFactory.getLogger(MybatisTemplate.class);

    private static MybatisSqlSessionFactory sessionFactory;

    public SqlSession getSession() {
        try {
            sessionFactory.createSqlSessionFactory(this.getClass());
            return MybatisSqlSessionFactory.sqlSessionFactory.openSession(true);
        } catch (IOException ex) {
            log.error(ex.getMessage(), ex);
        }
        return null;
    }

    public void setSessionFactory(MybatisSqlSessionFactory sessionFactory) {
        MybatisTemplate.sessionFactory = sessionFactory;
    }
}
