package com.example.camelexperiment.routes;

import com.example.camelexperiment.beans.Greeter;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

//@Component
public class TimerRoute extends RouteBuilder {
    private final Greeter greeter;

    public TimerRoute(Greeter greeter) {
        this.greeter = greeter;
    }

    @Override
    public void configure() throws Exception {
        // start from a timer
        from("timer:hello?period={{app.timer.period}}").routeId("hello")
                // and call the bean
                .bean(greeter, "greet")
                // and print it to system out via stream component
                .to("stream:out");
    }
}
