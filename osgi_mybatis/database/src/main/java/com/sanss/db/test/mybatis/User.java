package com.sanss.db.test.mybatis;

import org.apache.camel.Message;
import org.apache.camel.builder.xml.ResultHandler;

import javax.xml.transform.Result;

public class User {
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
