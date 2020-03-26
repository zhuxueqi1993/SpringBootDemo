package test;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class OneTest2Application {

	public static void main(String[] args) {
		SpringApplication.run(OneTest2Application.class, args);
	}

}
