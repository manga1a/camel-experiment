package com.example.camelexperiment.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class MessageRouter extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        from("direct:start")
                .routeId("greetings-route")
                .setBody(constant("Hello from Apache Camel!"))
                .to("stream:out");
    }
}
