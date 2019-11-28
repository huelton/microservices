package br.com.ms.communication.buyprocess;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;

@SpringBootApplication
@EnableCircuitBreaker
public class BuyprocessApplication {

	public static void main(String[] args) {
		SpringApplication.run(BuyprocessApplication.class, args);
	}

}
