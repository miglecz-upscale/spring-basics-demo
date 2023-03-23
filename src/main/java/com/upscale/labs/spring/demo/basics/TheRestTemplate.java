package com.upscale.labs.spring.demo.basics;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component("alma2")
@Qualifier("alma1")
public class TheRestTemplate extends RestTemplate {
}
