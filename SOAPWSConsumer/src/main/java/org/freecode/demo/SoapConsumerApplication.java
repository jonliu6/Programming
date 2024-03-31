package org.freecode.demo;

import org.freecode.demo.soapwsconsumer.wsdl.ConvertCelsiusToFahrenheitResponse;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SoapConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SoapConsumerApplication.class, args);
	}

	@Bean
	CommandLineRunner lookup(WeatherForecastClient client) {
		return args -> {
		      int celsiusValue = 100;

		      if (args.length > 0) {
		    	  celsiusValue = Integer.valueOf(args[0]);
		      }
		      ConvertCelsiusToFahrenheitResponse response = client.getFahrenheitValue(celsiusValue);
		      System.err.println("Fahrenheit: " + response.getConvertCelsiusToFahrenheitResult());
		    };
	}
}
