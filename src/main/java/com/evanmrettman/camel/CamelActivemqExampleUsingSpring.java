package com.evanmrettman.camel;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.spring.SpringCamelContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CamelActivemqExampleUsingSpring
{
    public static final void main(String[] args) throws Exception
    {
        // define endpoints and beans
        ConfigurableApplicationContext ctx_app = new ClassPathXmlApplicationContext("applicationContext.xml");
        CamelContext ctx_camel = SpringCamelContext.springCamelContext(ctx_app, false);
        // create camel context based on application context
        try
        { // start, print, and wait for all print messages
            ctx_camel.start();
            ProducerTemplate template = ctx_camel.createProducerTemplate();
            for (int i = 0; i < 5; i++)
                template.sendBody("activemq:queue:start", "body" + i);
            Thread.sleep(1000);
        }
        catch (Exception e)
        { // some error?
            System.out.println("error occurred: "+e.toString());
        }
        finally
        { // cleanup
            ctx_camel.stop();
            ctx_app.close();
        }
    }
}