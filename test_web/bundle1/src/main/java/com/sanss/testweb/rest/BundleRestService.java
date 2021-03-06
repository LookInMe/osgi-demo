package com.sanss.testweb.rest;


import com.sanss.testweb.bundle.MyActivator;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Map;

@Path("/")
public class BundleRestService {
    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getAllBunles(){
        Map<Integer, Integer> map = MyActivator.bundletype;
        return Response.ok(map).build();
    }
}
