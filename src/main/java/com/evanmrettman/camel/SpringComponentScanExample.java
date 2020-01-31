package com.evanmrettman.camel;

import org.apache.camel.CamelContext;
import org.apache.camel.spring.SpringCamelContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringComponentScanExample
{
    public static final void main(String[] args) throws Exception
    {

        ConfigurableApplicationContext ctx_app = new ClassPathXmlApplicationContext("springComponentScanApplicationContext.xml");
        CamelContext ctx_camel = SpringCamelContext.springCamelContext(ctx_app, false);
        try {
            ctx_camel.start();
            Thread.sleep(4000);
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
