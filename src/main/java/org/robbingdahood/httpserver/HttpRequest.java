package org.robbingdahood.httpserver;

import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Builder
@Getter
public class HttpRequest {
    private HttpMethod httpMethod;
    private String path;
    private String body;
    private Map<String, String> headers;
}
