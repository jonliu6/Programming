package org.freecode.demo.springbootaidemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootaidemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootaidemoApplication.class, args);
	}

	// @Bean
	// public CommandLineRunner runner(ChatClient.Builder builder) {
	// 	return args -> {
	// 		ChatClient chatClient = builder.build();
	// 		String response = chatClient.prompt("Tell me a joke").call().content();							
	// 		System.out.println(response);
	// 	};
	// }

}
