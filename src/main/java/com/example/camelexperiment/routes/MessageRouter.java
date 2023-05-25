package com.example.camelexperiment.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class MessageRouter extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        from("direct:b2be.poa")
                .routeId("b2be.poa")
                .setBody(constant("This is B2BE POA route"))
                .to("stream:out");

        from("direct:b2be.asn")
                .routeId("b2be.asn")
                .setBody(constant("This is B2BE ASN route"))
                .to("stream:out");

        from("direct:b2be.invoice")
                .routeId("b2be.invoice")
                .setBody(constant("This is B2BE Invoice route"))
                .to("stream:out");

        from("direct:b2be")
                .routeId("b2be")
                .choice()
                .when(simple("${header.messageType} == 'poa'"))
                .to("direct:b2be.poa")
                .when(simple("${header.messageType} == 'asn'"))
                .to("direct:b2be.asn")
                .when(simple("${header.messageType} == 'invoice'"))
                .to("direct:b2be.invoice")
                .otherwise()
                .to("stream:out");
    }
}
