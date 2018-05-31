package sanss.bmt.test.rest.flow;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
public class FlowServiceRest {
    Logger logger = Logger.getLogger(FlowServiceRest.class);

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFlow() {
        String res = "{'res':'flow success'}";
        ObjectMapper mapper = new ObjectMapper();
        try {
            res = mapper.writeValueAsString(res);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        logger.info("test log ");
        return Response.ok(res).build();
    }
}
