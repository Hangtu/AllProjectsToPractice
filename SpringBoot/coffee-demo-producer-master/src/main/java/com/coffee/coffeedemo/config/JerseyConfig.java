package com.coffee.coffeedemo.config;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

import com.coffee.coffeedemo.resource.CoffeeResource;
import com.coffee.coffeedemo.resource.ResponseFilter;

@Configuration 
@ApplicationPath("/rest")
public class JerseyConfig extends ResourceConfig {
	
	public JerseyConfig()
    {
		register(CoffeeResource.class);
		register(ResponseFilter.class);
    }
	
}
