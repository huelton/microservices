package br.com.ms.communication.buyfeedback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@SpringBootApplication
@EnableRedisRepositories
@EnableDiscoveryClient
public class BuyfeedbackApplication {

	public static void main(String[] args) {
		SpringApplication.run(BuyfeedbackApplication.class, args);
	}

}
