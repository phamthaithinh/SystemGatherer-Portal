package io.github.systemgatherer.configuration;

import io.dropwizard.Configuration;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.List;

/**
 * @author Rinat Muhamedgaliev aka rmuhamedgaliev
 */
@Data
public class SGConfiguration extends Configuration {
    @NotEmpty
    private List<Host> hosts;

    public SGConfiguration() {
    }

    public SGConfiguration(List<Host> hostses) {
        this.hosts = hosts;
    }
}