package io.github.systemgatherer.response;

import lombok.Data;

import java.util.Date;

/**
 * @author Rinat Muhamedgaliev aka rmuhamedgaliev
 */
@Data
public class Response {
    private final int code;
    private final Date date;
    private final Object info;

    public Response(int code, Date date, Object info) {
        this.code = code;
        this.date = date;
        this.info = info;
    }
}
