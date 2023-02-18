package spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"data", "domain", "spring", "util" })
public class SpringRestMavenJavaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringRestMavenJavaApplication.class, args);
	}

}
