package io.github.systemgatherer;

import com.hubspot.dropwizard.guice.GuiceBundle;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.github.systemgatherer.configuration.SGConfiguration;
import io.github.systemgatherer.notifier.NotificationModule;
import io.github.systemgatherer.util.CorsHeadersFilter;
import io.github.systemgatherer.web.Root;
import org.eclipse.jetty.servlets.CrossOriginFilter;

import javax.servlet.DispatcherType;
import java.util.EnumSet;

/**
 * @author Rinat Muhamedgaliev aka rmuhamedgaliev
 */
public class SystemGatherer extends Application<SGConfiguration> {
    @Override
    public void initialize(Bootstrap<SGConfiguration> bootstrap) {
        GuiceBundle<SGConfiguration> guiceBundle  = GuiceBundle.<SGConfiguration>newBuilder()
            .addModule(new NotificationModule())
                .setConfigClass(SGConfiguration.class)
                .enableAutoConfig(getClass().getPackage().getName())
                .build();
        bootstrap.addBundle(guiceBundle);
    }

    @Override
    public void run(SGConfiguration configuration, Environment environment) throws Exception {
        final Root root = new Root(configuration);
        environment.servlets().addFilter("CorsFilter", new CorsHeadersFilter()).addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
        environment.servlets().addFilter("CORS", new CrossOriginFilter()).addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
    }

    public static void main(String[] args) throws Exception {
        new SystemGatherer().run(args);
    }
}
