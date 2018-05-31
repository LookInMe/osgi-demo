package com.sanss.db.test.config;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Config {

    private static Logger logger = Logger.getLogger(Config.class);

    /**
     * @param @return
     * @return Map<String   ,   String>    返回类型
     * @throws
     * @Title: getSqlConfig
     * @Description: TODO(读取sqlmapconfig.xml配置文件)
     */
    public static Map<String, String> getSqlConfig() {
        Map<String, String> map = new HashMap<String, String>();
        try {
            SAXReader reader = new SAXReader();
            reader.setEntityResolver(new IgnoreDTDEntityResolver()); // ignore dtd
            Document document = null;
            try {
                //获取项目路径下的文件
                document = reader.read("mybatis.xml");

            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("config有问题");
            }
            Element root = document.getRootElement();
            Element element = root.element("environments").element("environment").element("dataSource");
            List<Element> elements = element.elements();

            for (Element e : elements) {
                map.put(e.attribute("name").getText(), e.attribute("value").getText());
            }
            String pageName = root.element("mappers").element("package").attribute("name").getText();
            map.put("pageName", pageName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

}
