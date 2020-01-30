package com.evanmrettman.camel;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.spring.SpringCamelContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ActiveMqRouteBuilderExample
{
    public static void main(String[] args) throws Exception
    {
        ConfigurableApplicationContext ctx_app = new ClassPathXmlApplicationContext("applicationContext.xml");
        CamelContext ctx_camel = SpringCamelContext.springCamelContext(ctx_app,false);
        try
        {
            ctx_camel.start();
            ProducerTemplate template = ctx_camel.createProducerTemplate();
            for (int i = 0; i < 5; i++)
                template.sendBody("activemq:queue:start","body"+i);
            Thread.sleep(1000);
        }
        catch (Exception e)
        {
            System.out.println("error occurred: "+e.toString());
        }
        finally
        {
            ctx_camel.stop();
            ctx_app.close();
        }
    }
}
