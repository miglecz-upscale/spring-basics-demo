package com.upscale.labs.spring.demo.basics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.client.RestTemplate;

@org.springframework.context.annotation.Configuration
@ComponentScan("com.upscale.labs.spring.demo.basics")
@PropertySource("application.yaml")
@PropertySource("env-${environment}.yaml")
public class Configuration {

    @Bean(name = "rest1")
    @Qualifier("client1")
    RestTemplate restClient1() {
        return new RestTemplate() {{
            List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
            MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
            converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
            messageConverters.add(converter);
            this.setMessageConverters(messageConverters);
        }};
    }

    @Bean
    RestTemplate restClient2() {
        return new RestTemplate();
    }

    @Bean
    JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(new DriverManagerDataSource() {{
                setDriverClassName("org.postgresql.Driver");
                setUrl("jdbc:postgresql:postgres");
                setUsername("postgres");
                setPassword("adminadmin");
            }}
        );
    }
}
