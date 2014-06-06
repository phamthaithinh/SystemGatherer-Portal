package io.github.systemgatherer.configuration;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.List;

@Data
public class Config {
    @NotEmpty
    private List<Host> hosts;
    private Email email;

    public Config() {
    }

    public Config(List<Host> hostses, Email email) {
        this.hosts = hosts;
        this.email = email;
    }
}
