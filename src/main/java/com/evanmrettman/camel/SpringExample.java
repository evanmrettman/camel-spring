package com.evanmrettman.camel;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringExample
{
    public static void main(String[] args) throws Exception
    {
        try (ConfigurableApplicationContext ctx_app = new ClassPathXmlApplicationContext("applicationContext.xml"))
        { // load in application context
            TestBean testBean = (TestBean) ctx_app.getBean("testBean");  // instantiate bean
            System.out.println(testBean.hello("Camel and Spring"));
        }
    }
}
