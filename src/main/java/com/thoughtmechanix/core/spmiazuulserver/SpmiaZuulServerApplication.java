package com.thoughtmechanix.core.spmiazuulserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableZuulProxy
@SpringBootApplication
public class SpmiaZuulServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpmiaZuulServerApplication.class, args);
	}

}
