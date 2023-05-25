package com.example.camelexperiment.routes;

import org.apache.camel.EndpointInject;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.spring.junit5.CamelSpringBootTest;
import org.apache.camel.test.spring.junit5.MockEndpoints;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@CamelSpringBootTest
@MockEndpoints("stream:out")
class MessageRouterTest {

    @Autowired
    private ProducerTemplate template;

    @EndpointInject("mock:stream:out")
    private MockEndpoint mock;

    @BeforeEach
    void beforeEach() {
        mock.reset();
    }

    @Test
    void whenPoaMessageType_thenPoaMessageReceived() throws InterruptedException {
        mock.expectedBodiesReceived("This is B2BE POA route");

        template.sendBodyAndHeader("direct:b2be", null, "messageType", "poa");

        mock.assertIsSatisfied();
    }

    @Test
    void whenAsnMessageType_thenAsnMessageReceived() throws InterruptedException {
        mock.expectedBodiesReceived("This is B2BE ASN route");

        template.sendBodyAndHeader("direct:b2be", null, "messageType", "asn");

        mock.assertIsSatisfied();
    }

    @Test
    void whenInvoiceMessageType_thenInvoiceMessageReceived() throws InterruptedException {
        mock.expectedBodiesReceived("This is B2BE Invoice route");

        template.sendBodyAndHeader("direct:b2be", null, "messageType", "invoice");

        mock.assertIsSatisfied();
    }
}
