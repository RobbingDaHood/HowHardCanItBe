package org.robbingdahood.httpserver;

import java.util.Map;
import java.util.function.Function;

public interface PathObserver {
    Map<String, Function<HttpRequest, HttpResponse>> getPaths();
}
