package org.acme;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.logging.Logger;

@Path("/hello")
public class ExampleResource {

    private static final Logger LOG = Logger.getLogger(ExampleResource.class);

    @ConfigProperty(name = "greeting.message", defaultValue = "hello")
    String message;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello(@QueryParam("param") String param) {
        String prenom = param.split(",")[0];
        LOG.infov("Passage dans Hello [{0}]", param);
        return message + " " + prenom + "\n";
    }
}
