package org.pes.onecemulator;

import com.google.common.eventbus.AsyncEventBus;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.concurrent.Executors;

@SpringBootApplication
@EnableAsync
public class Application {

	@Bean
	public AsyncEventBus asyncEventBus() { return new AsyncEventBus("asyncDefault", Executors.newFixedThreadPool(3)); }

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
