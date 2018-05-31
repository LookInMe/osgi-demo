package com.sanss.db.test.service;

import com.sanss.db.test.config.MyBatisHelper;
import com.sanss.db.test.dao.UserMapper;
import com.sanss.db.test.entity.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
public class UserServiceImpl {
    Logger logger = Logger.getLogger(UserServiceImpl.class);

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser(@PathParam("id") Integer id) {
        logger.info("===============id:" + id);
        SqlSession session = MyBatisHelper.initMybatis().openSession();
        UserMapper mapper = session.getMapper(UserMapper.class);
        User user = mapper.getUser(id);
        session.close();
        return Response.ok(user).build();
    }

}
