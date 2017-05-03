package org.eforum.front.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.async.DeferredResult;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2Configuration {
	private static Logger LOG = LoggerFactory.getLogger(Swagger2Configuration.class);
	
	@Bean
	public Docket restApi() {
		LOG.info("配置Swagger2");
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("eforum")
				.genericModelSubstitutes(DeferredResult.class)
				.useDefaultResponseMessages(false)
				.forCodeGeneration(true)
				.pathMapping("/")   // 设置请求地址，默认访问页面地址是/swagger-ui.html
				.select()
				.paths(PathSelectors.regex("/.*"))   //过滤的接口
				.build()
				.apiInfo(restApiInfo());
	}
	
	private ApiInfo restApiInfo() {
		Contact contact = new Contact("error", "https://github.com/EnjoyError", "345458517@qq.com");
		ApiInfo apiInfo = new ApiInfo("前端API接口",   //大标题
			"eforum",       // 小标题
			"0.0.1",        // 版本
			"https://github.com/EnjoyError/eforum",
			contact,        // 作者
			"GPL-3.0",
			"http://www.gnu.org/licenses/gpl-3.0.html"
        );
        return apiInfo;
	}
}