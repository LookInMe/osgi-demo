package com.sanss.db.test.dao;

import com.sanss.db.test.entity.User;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {

    @Select("select * from user where id=#{id}")
    public User getUser(Integer id);
}
