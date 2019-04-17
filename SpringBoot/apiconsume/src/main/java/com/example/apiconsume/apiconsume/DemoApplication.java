package com.example.apiconsume.apiconsume;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import java.util.Arrays;
import com.example.apiconsume.apiconsume.user.MyResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class DemoApplication {

	private static final Logger log = LoggerFactory.getLogger(DemoApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);

		// You are trying to access https without configuring RestTemplate to use any
		// SSL certificate for request
		// Try to use following configuration of RestTemplate
		// CloseableHttpClient httpClient =
		// HttpClients.custom().setSSLHostnameVerifier(new NoopHostnameVerifier())
		// .build();
		// HttpComponentsClientHttpRequestFactory requestFactory = new
		// HttpComponentsClientHttpRequestFactory();
		// requestFactory.setHttpClient(httpClient);
		// RestTemplate restTemplate = new RestTemplate();
		// Data quote = restTemplate.getForObject("http://reqres.in/api/users/2",
		// Data.class);
		// System.out.println(quote);

		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.add("user-agent",
				"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, likeGecko) Chrome/54.0.2840.99 Safari/537.36");
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
		ResponseEntity<Object> response = restTemplate.exchange("https://reqres.in/api/users/2", HttpMethod.GET,
				entity, Object.class);
		System.out.println(response.getStatusCode());
		System.out.println(response.getStatusCodeValue());
		System.out.println(response.getBody());
		//MyResponse data = response.getBody();
		//System.out.println(response);
		//System.out.println("final");
		//System.out.println(data.getData().getUser().getFirst_name());
		// {data={id=2, first_name=Janet,
		// last_name=Weaver,avatar=https://s3.amazonaws.com/uifaces/faces/twitter/josephstein/128.jpg}}
	}

}
