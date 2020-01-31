package com.evanmrettman.camel;

import org.apache.camel.CamelContext;
import org.apache.camel.spring.SpringCamelContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CamelPackageScanUsingSpring
{
    public static void main(String[] args) throws Exception
    {
        ConfigurableApplicationContext ctx_app = new ClassPathXmlApplicationContext("springPackageScanApplicationContext.xml");
        CamelContext ctx_camel = SpringCamelContext.springCamelContext(ctx_app,false);

        try
        {
            ctx_camel.start();
            Thread.sleep(3000);
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
