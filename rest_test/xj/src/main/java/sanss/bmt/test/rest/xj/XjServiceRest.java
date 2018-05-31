package sanss.bmt.test.rest.xj;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
public class XjServiceRest {

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getXj(){
        String res = "\"{'res':'Xj rest get'}\"";
        /*System.out.println("first:"+res);
        ObjectMapper mapper = new ObjectMapper();

        try {
            res = mapper.writeValueAsString(res);
        } catch (JsonProcessingException e) {

            e.printStackTrace();
            return null;
        }
        System.out.println("last:"+res);*/
        return Response.ok(res).build();
    }
}
