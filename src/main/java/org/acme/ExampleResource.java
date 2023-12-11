package org.acme;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
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
