package io.chico;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UpContainerApplication {

	public static void main(String[] args) {
		SpringApplication.run(UpContainerApplication.class, args);
		CouchBaseContainerRunner.startUpDefault();
	}
}
