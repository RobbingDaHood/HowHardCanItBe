package org.robbingdahood.httpserver;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PathListener {
    private static List<PathObserver> pathObservers = new ArrayList<>(); //TODO thread safety

    private PathListener() {

    }

    public static void addObserver(PathObserver pathObserver) {
        //TODO no overlap in paths

        pathObservers.add(pathObserver);
    }

    public static HttpResponse triggerObserver(HttpRequest httpRequest) {
        return pathObservers.stream()
                .map(PathObserver::getPaths)
                .map(stringFunctionMap -> stringFunctionMap.get(httpRequest.getPath())) //TODO handle query params etc.
                .filter(Objects::nonNull)
                .map(httpRequestHttpResponseFunction -> httpRequestHttpResponseFunction.apply(httpRequest))
                .findFirst()
                .orElseThrow();
    }
}
