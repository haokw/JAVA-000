package io.gateway.router;


public interface HttpEndpointRouter {
    static public String route(String url) {
        return url + "/api/hello";
    };
}
