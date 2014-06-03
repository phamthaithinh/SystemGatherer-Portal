package io.github.systemgatherer.notifier;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.name.Named;
import io.github.systemgatherer.configuration.Host;
import io.github.systemgatherer.configuration.SGConfiguration;
import io.github.systemgatherer.monitor.IRequester;
import io.github.systemgatherer.monitor.impl.Requester;
import io.github.systemgatherer.notifier.impl.Notifier;

import java.util.List;

public class NotificationModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(INotifier.class).to(Notifier.class);
        bind(IRequester.class).to(Requester.class);
    }

    @Provides
    @Named("emailHost")
    public String provideEmailHost(SGConfiguration sgConfiguration) {
        return sgConfiguration.getEmail().getHost();
    }

    @Provides
    @Named("emailPort")
    public int provideEmailPort(SGConfiguration sgConfiguration) {
        return sgConfiguration.getEmail().getPort();
    }

    @Provides
    @Named("emailUsername")
    public String provideEmailUsername(SGConfiguration sgConfiguration) {
        return sgConfiguration.getEmail().getUsername();
    }

    @Provides
    @Named("emailPassword")
    public String provideEmailPassword(SGConfiguration sgConfiguration) {
        return sgConfiguration.getEmail().getPassword();
    }

    @Provides
    @Named("hosts")
    public List<Host> hosts(SGConfiguration sgConfiguration) {
        return sgConfiguration.getHosts();
    }
}
