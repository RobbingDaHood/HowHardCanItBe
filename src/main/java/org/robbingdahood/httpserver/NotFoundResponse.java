package org.robbingdahood.httpserver;

import lombok.experimental.Delegate;

public class NotFoundResponse implements HttpResponse {
    @Delegate
    private GeneralHttpResponse generalHttpResponse;

    public NotFoundResponse() {
        generalHttpResponse = GeneralHttpResponse.builder()
                .statusCode(404)
                .statusMessage("NOT FOUND")
                .build();
    }
}
