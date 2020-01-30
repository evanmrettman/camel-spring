package com.evanmrettman.camel;

import org.apache.activemq.camel.component.ActiveMQComponent;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.support.jndi.JndiContext;

public class CamelActiveMqExample
{
    public static void main(String[] args) throws Exception
    {
        JndiContext ctx_jndi = new JndiContext(); // Java Naming and Directory Interface init
        ctx_jndi.bind("testBean", new TestBean()); // link testBean to jndi

        CamelContext ctx_camel = new DefaultCamelContext(ctx_jndi); // pass testbean reference ctx into camel
        ctx_camel.addComponent("activemq",ActiveMQComponent.activeMQComponent("vm://localhost?broker.persistent=false"));

        try
        {
            ctx_camel.addRoutes(new RouteBuilder()
            {
                @Override
                public void configure() throws Exception
                {
                    from("activemq:queue:start")
                            .to("bean:testBean?method=hello") // when started, run method hello
                            .to("stream:out"); // to output stream
                }
            });

            ProducerTemplate template = ctx_camel.createProducerTemplate(); // allows endpoint communication
            ctx_camel.start(); // start running the context
            for(int i = 0; i < 5; i ++)
            {
                template.sendBody("activemq:queue:start", "body" + i); // communicate to test bean ctx
            }
            Thread.sleep(1000); // wait for printed messages

        }
        catch (Exception e)
        {
            System.out.println("error occurred");
        }
        finally
        {
            ctx_camel.stop();
        }
    }
}
