package com.evanmrettman.camel.routes;

import org.apache.camel.builder.RouteBuilder;

public class SimpleRouteBuilder extends RouteBuilder
{
    public void configure()
    {
        from("timer:foo?period=1s")
                .transform()
                .simple("Heartbeat ${date:now:yyyy-MM-dd HH:mm:ss}")
                .to("stream:out");
    }
}
