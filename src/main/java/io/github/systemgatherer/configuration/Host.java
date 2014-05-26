package io.github.systemgatherer.configuration;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author Rinat Muhamedgaliev aka rmuhamedgaliev
 */
@Data
public class Host {
    @NotEmpty
    private String name;
    @NotEmpty
    private String ip;
    @NotEmpty
    private String[] checks;


    public Host() {
    }

    public Host(String name, String ip, String[] checks) {
        this.name = name;
        this.ip = ip;
        this.checks = checks;
    }
}
