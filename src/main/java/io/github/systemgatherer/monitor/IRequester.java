package io.github.systemgatherer.monitor;

import io.github.systemgatherer.configuration.Host;

/**
 * @author Rinat Muhamedgaliev aka rmuhamedgaliev
 */
public interface IRequester {

    String getStatus(Host host);
}
