package com.evanmrettman.camel;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringExample {
    public static void main(String[] args) throws Exception {
        try (ConfigurableApplicationContext appContext = new ClassPathXmlApplicationContext("applicationContext.xml")) {
            TestBean testBean = (TestBean) appContext.getBean("testBean");
            System.out.println(testBean.hello("Camel and Spring"));
        }
    }
}
