package com.upscale.labs.spring.demo.basics;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class Application {
    @Autowired
    //@Qualifier("rest1")
    @Qualifier("client1")
    //@Qualifier("restClient1") // no
    RestTemplate restClient1;
    @Autowired
    @Qualifier("restClient2")
    RestTemplate restClient2;
    @Autowired
    @Qualifier("alma2")
    TheRestTemplate theRestTemplate;

    @Value("${environment}")
    String environment;

    @Value("${a}")
    String a;

    @Autowired
    JdbcTemplate jdbcTemplate;

    /**
     * Output:
     * hi
     * restClient1 = com.upscale.labs.spring.demo.basics.Configuration$1@7cb502c
     * restClient2 = org.springframework.web.client.RestTemplate@275bf9b3
     * theRestTemplate = com.upscale.labs.spring.demo.basics.TheRestTemplate@1b8a29df
     * environment = prod
     * a = 1
     * responsev1.getBody() = Hello, World!
     * responsev2.getBody() = bar
     * responseAlma = Alma(color=piros)
     * itemName = Chieftek 1000W tápegység
     * itemsName = [Chieftek 1000W tápegység]
     * almas = [Alma(color=Chieftek 1000W tápegység)]
     */
    void run() {
        System.out.println("hi");

        //RestTemplate restClient1 = (RestTemplate) context.getBean("rest1");
        System.out.println("restClient1 = " + restClient1);
        //RestTemplate restClient2 = (RestTemplate) context.getBean("restClient2");
        System.out.println("restClient2 = " + restClient2);
        //TheRestTemplate theRestTemplate =
        //    //(TheRestTemplate) context.getBean("TheRestTemplate");
        //    //(TheRestTemplate) context.getBean("alma2");
        //    (TheRestTemplate) context.getBean("alma1");
        //    //context.getBean(TheRestTemplate.class);
        System.out.println("theRestTemplate = " + theRestTemplate);
        System.out.println("environment = " + environment);
        System.out.println("a = " + a);
        ResponseEntity<String> responsev1 = restClient2.getForEntity("http://localhost:8080/api/v1/items", String.class);
        System.out.println("responsev1.getBody() = " + responsev1.getBody());

        HttpEntity<String> request = new HttpEntity<>("bar");
        ResponseEntity<String> responsev2 = restClient2.postForEntity("http://localhost:8080/api/v1/items", request, String.class);
        System.out.println("responsev2.getBody() = " + responsev2.getBody());

        Alma responseAlma = restClient1.getForObject("http://localhost:8080/api/v1/alma", Alma.class);
        System.out.println("responseAlma = " + responseAlma);

        String itemName = jdbcTemplate.queryForObject("SELECT name FROM items LIMIT 1", String.class);
        System.out.println("itemName = " + itemName);

        List<String> itemsName = jdbcTemplate.queryForList("SELECT name FROM items", String.class);
        System.out.println("itemsName = " + itemsName);

        List<Alma> almas = getAlmas();
        System.out.println("almas = " + almas);
    }

    private List<Alma> getAlmas() {
        return jdbcTemplate.query("SELECT name AS color FROM items", new BeanPropertyRowMapper<>(Alma.class));
    }
}
