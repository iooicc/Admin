package my.adminTest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication(scanBasePackages={"my"})
@EnableAsync
public class AdminTestApplication {
	public static void main(String[] args) {
		SpringApplication.run(AdminTestApplication.class, args);
	}
}
