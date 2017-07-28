package org.eforum.front.config;

import org.eforum.front.resolvers.MyArgumentsResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommonConfiguration {
	@Bean
	public MyArgumentsResolver configAutoLoad() {
		return new MyArgumentsResolver();
	}
}
