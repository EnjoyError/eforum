package org.eforum.front.config;

import java.util.ArrayList;
import java.util.List;

import org.eforum.front.resolvers.MyArgumentsResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

@Configuration
public class CommonConfiguration {
	@Autowired
	private MyArgumentsResolver resolver;

	public void setResolver(MyArgumentsResolver resolver) {
		this.resolver = resolver;
	}

	@Bean
	public RequestMappingHandlerAdapter configAutoLoad() {
		RequestMappingHandlerAdapter handlerAdapter = new RequestMappingHandlerAdapter();
		List<HandlerMethodArgumentResolver> list = new ArrayList<HandlerMethodArgumentResolver>();
		list.add(resolver);
		handlerAdapter.setCustomArgumentResolvers(list);
		return handlerAdapter;
	}
}
