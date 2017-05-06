package org.eforum.front.config;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;

@Configuration
public class FrontWebMvcConfiguration extends WebMvcConfigurerAdapter {
	private static final Logger LOG = LoggerFactory.getLogger(FrontWebMvcConfiguration.class);
	
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		LOG.info("配置fastJson");
		FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
		FastJsonConfig fastJsonConfig = new FastJsonConfig();
		fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
		fastConverter.setFastJsonConfig(fastJsonConfig);
		converters.add(fastConverter);
		super.configureMessageConverters(converters);
	}
}