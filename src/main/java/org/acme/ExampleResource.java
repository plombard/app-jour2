package org.acme;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.jboss.logging.Logger;

@Path("/hello")
public class ExampleResource {

    private static final Logger LOG = Logger.getLogger(ExampleResource.class);

    @ConfigProperty(name = "greeting.message", defaultValue = "hello")
    String message;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    /* FIX 001 : L'application propose une véritable API : documentation et paramètres typés. */
    @Operation(description = "Retourne un bonjour avec le prenom passé en paramètre")
    public String hello(@Parameter(description = "Nom de la personne", required = true) @QueryParam("nom") String nom,
            @Parameter(description = "Prénom de la personne", required = true) @QueryParam("prenom") String prenom) {
        LOG.infov("Passage dans Hello [{0}]", nom);
        return message + " " + prenom + "\n";
    }
}
