package io.github.systemgatherer.web;

import io.github.systemgatherer.configuration.SGConfiguration;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * @author Rinat Muhamedgaliev aka rmuhamedgaliev
 */
@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class Root {

    private final SGConfiguration configuration;

    public Root(SGConfiguration configuration) {
        this.configuration = configuration;
    }

    @GET
    @Path("/hosts")
    public List<io.github.systemgatherer.configuration.Host> hosts() {
        return configuration.getHosts();
    }
}
