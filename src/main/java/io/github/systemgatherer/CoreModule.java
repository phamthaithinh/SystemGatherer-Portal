package io.github.systemgatherer;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import io.github.systemgatherer.notifier.NotificationModule;
import io.github.systemgatherer.web.Root;

public class CoreModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(Root.class).in(Singleton.class);

        install(new NotificationModule());
    }
}
