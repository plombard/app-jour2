package org.acme;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.metrics.MetricUnits;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Timed;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.jboss.logging.Logger;

@Path("/hello")
public class ExampleResource {

    private static final Logger LOG = Logger.getLogger(ExampleResource.class);

    @ConfigProperty(name = "greeting.message")
    String message;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    /* FIX 001 : L'application propose une véritable API : documentation et paramètres typés. */
    @Operation(description = "Retourne un bonjour avec le prenom passé en paramètre")
    /* FIX 004 : Exposition de métriques et indicateurs de performance de l'application */
    @Counted(name = "hellosPerformed", description = "Combien de hellos effectués.")
    @Timed(name = "hellosTimer", description = "En combien de temps s'effectue un hello", unit = MetricUnits.MILLISECONDS)
    public String hello(@Parameter(description = "Nom de la personne", required = true) @QueryParam("nom") String nom,
            @Parameter(description = "Prénom de la personne", required = true) @QueryParam("prenom") String prenom) {
        LOG.infov("Passage dans Hello [{0}]", nom);
        if (prenom != null) {
            return message + " " + prenom + "\n";
        } else {
            return message + "\n";
        }
        
    }
}
