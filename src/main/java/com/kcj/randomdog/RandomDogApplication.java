package com.kcj.randomdog;

import com.kcj.ui.UserInterface;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

import java.io.IOException;

@SpringBootApplication
@EnableScheduling
public class RandomDogApplication {
	private static UserInterface ui = new UserInterface();
	public static void main(String[] args) throws IOException {
		SpringApplication.run(RandomDogApplication.class, args);
		javafx.application.Application.launch(UserInterface.class, args);
	}


}
