package com.upscale.labs.spring.demo.basics;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        // https://docs.oracle.com/javase/tutorial/essential/exceptions/tryResourceClose.html
        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Configuration.class)) {
            context.getBean(Application.class).run();
            //RestTemplate restClient1 = (RestTemplate) context.getBean("rest1");
            //System.out.println("restClient1 = " + restClient1);
            //RestTemplate restClient2 = (RestTemplate) context.getBean("restClient2");
            //System.out.println("restClient2 = " + restClient2);
            //TheRestTemplate theRestTemplate =
            //    //(TheRestTemplate) context.getBean("TheRestTemplate");
            //    //(TheRestTemplate) context.getBean("alma2");
            //    (TheRestTemplate) context.getBean("alma1");
            //    //context.getBean(TheRestTemplate.class);
            //System.out.println("theRestTemplate = " + theRestTemplate);
        }
    }
}
