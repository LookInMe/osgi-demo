package com.sanss.db.test.mybatis;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/")
public class UserService {

    private MybatisTemplate mybatisTemplate;

    public void setMybatisTemplate(MybatisTemplate mybatisTemplate) {
        this.mybatisTemplate = mybatisTemplate;
    }

    @GET
    @Path("/user")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        List<User> orders = mybatisTemplate.getSession().selectList("selectUsers", new User());
        return Response.ok(orders).build();
    }

    @POST
    @Path("/user")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addUser(User user) {
        int num = mybatisTemplate.getSession().insert("insertUser", user);
        return Response.ok(num).build();
    }

    @GET
    @Path("/test")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllTest() {
        List<Test> tests = mybatisTemplate.getSession().selectList("selectTests", new Test());
        return Response.ok(tests).build();
    }

    @POST
    @Path("/test")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addTest(Test test) {
        int num = mybatisTemplate.getSession().insert("insertTest", test);
        return Response.ok(num).build();
    }
}
