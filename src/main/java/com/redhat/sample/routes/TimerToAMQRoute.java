package com.redhat.sample.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class TimerToAMQRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        // generate event
        from("timer://foo?period=1000").id("timerEndpoint").routeId("timer-activemq-route")
                // set some body
                .setBody().simple("hi, this is a legacy data to activemq for saving as file").id("route-setBody")
                // logging step
                .log("Timer endpoint triggered..").id("producer-log")
                // send to activemq over myQ queue
                .to("activemq:queue:my-queue").id("amq-producer");
    }

}