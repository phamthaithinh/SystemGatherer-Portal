package io.github.systemgatherer.monitor;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author Rinat Muhamedgaliev aka rmuhamedgaliev
 */
@Data
public class Response {
    @JsonProperty("code")
    private final int code;
    @JsonProperty("date")
    private final Date date;
    @JsonProperty("info")
    private final Object info;
    @JsonProperty("name")
    private final String name;

    public Response(@JsonProperty("code") int code,@JsonProperty("date") Date date,@JsonProperty("info") Object info,@JsonProperty("name") String name) {
        this.code = code;
        this.date = date;
        this.info = info;
        this.name = name;
    }
}
