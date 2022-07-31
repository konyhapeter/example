package integration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(scanBasePackages = "integration", exclude = {SecurityAutoConfiguration.class })
public class SpringIntegrationHelloApp implements CommandLineRunner {
	@Autowired
	@Qualifier("helloGateway")
	private HelloService helloGateway;

	@Override
	public void run(String... args) throws Exception {
		System.out.println(helloGateway.sayHello("Pett"));
	}

//	public static void main(String[] args) {
//		SpringApplication.run(SpringIntegrationHelloApp.class, args);
//	}
}
