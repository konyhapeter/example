package integration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.handler.ServiceActivatingHandler;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

@Configuration
public class HelloIntegrationConfig {
	@Bean("channel")
	public MessageChannel channel() {
		return new DirectChannel();
	}

	@Bean
	public HelloService getHelloService() {
		return new HelloServiceImpl();
	}

	@Bean
	@ServiceActivator(inputChannel = "channel")
	public MessageHandler serviceActivator() {
		return new ServiceActivatingHandler(getHelloService(), "sayHello");
	}
}
