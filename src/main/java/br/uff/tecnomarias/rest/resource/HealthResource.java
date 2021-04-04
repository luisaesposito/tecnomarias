package br.uff.tecnomarias.rest.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

@Path("health")
public class HealthResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response teste(){
        Map<String, String> json = new HashMap<String, String>();
        json.put("status", "OK");
        return Response.ok(json).build();
    }
}
