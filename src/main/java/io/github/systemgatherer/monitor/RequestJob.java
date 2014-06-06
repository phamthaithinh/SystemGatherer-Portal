package io.github.systemgatherer.monitor;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.name.Named;
import com.google.inject.name.Names;
import io.github.systemgatherer.configuration.Host;
import io.github.systemgatherer.configuration.SGConfiguration;
import io.github.systemgatherer.monitor.impl.Requester;
import io.github.systemgatherer.notifier.NotificationModule;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.List;

public class RequestJob implements Job {

    private final SGConfiguration configuration;

    @Inject
    public RequestJob(SGConfiguration configuration) {
        this.configuration = configuration;
    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        IRequester requester = new Requester();
        for (Host host: configuration.getHosts()) {
            requester.getStatus(host);
        }
    }
}
