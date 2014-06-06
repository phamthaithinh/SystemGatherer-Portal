package io.github.systemgatherer.configuration;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

@Data
public class Email {
    @NotEmpty
    private String host;
    @NotNull
    private int port;
    @NotEmpty
    private String username;
    @NotEmpty
    private String password;

    public Email() {
    }

    public Email(String host, int port, String username, String password) {
        this.host = host;
        this.port = port;
        this.username = username;
        this.password = password;
    }
}
