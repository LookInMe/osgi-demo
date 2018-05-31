package com.sanss.db.test.mybatis;

import org.apache.ibatis.builder.xml.XMLMapperBuilder;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

public class MybatisSqlSessionFactory {
    private final static Logger log = LoggerFactory.getLogger(MybatisSqlSessionFactory.class);
    public static SqlSessionFactory sqlSessionFactory;
    private SqlMapConfig config;

    public void createSqlSessionFactory(Class classLoader) throws IOException {
        if (sqlSessionFactory == null) {
            Properties properties = new Properties();
            properties.setProperty("driver", config.getDriver());
            properties.setProperty("url", config.getUrl().replace('#', '='));
            properties.setProperty("username", config.getUsername());
            properties.setProperty("password", config.getPassword());
            properties.setProperty("poolMaximumActiveConnections", config.getPoolMaximumActiveConnections());
            properties.setProperty("poolMaximumIdleConnections", config.getPoolMaximumIdleConnections());
            log.info(properties.getProperty("url"));
            try {
                InputStream configFileInputStream = classLoader.getResourceAsStream("/sqlMapConfig.xml");
                sqlSessionFactory = new SqlSessionFactoryBuilder().build(configFileInputStream, properties);

                Configuration configuration = sqlSessionFactory.getConfiguration();

                InputStream mapperFileInputStream;
                mapperFileInputStream = classLoader.getResourceAsStream("/Mapper.xml");
                if (mapperFileInputStream == null) {
                    throw new Exception("can not find Mapper.xml in resource /mybatis");
                }
                SAXReader read = new SAXReader();
                Document document = read.read(mapperFileInputStream);
                List list = document.getRootElement().elements();
                for (Iterator it = list.iterator(); it.hasNext(); ) {
                    Element element = (Element) it.next();
                    String resource = element.attributeValue("resource");
                    InputStream inputStream;
                    inputStream = classLoader.getResourceAsStream(resource);
                    if (inputStream == null) {
                        throw new Exception("can not find " + resource);
                    }
                    XMLMapperBuilder mapperParser = new XMLMapperBuilder(inputStream, configuration, resource, configuration.getSqlFragments());
                    mapperParser.parse();
                    inputStream.close();
                }
                mapperFileInputStream.close();
            } catch (DocumentException ex) {
                log.error(ex.getMessage(), ex);
            } catch (Exception ex) {
                log.error(ex.getMessage(), ex);
            }
        }
    }

    public void setConfig(SqlMapConfig config) {
        this.config = config;
    }
}
