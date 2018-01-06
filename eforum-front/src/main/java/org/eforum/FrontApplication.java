package org.eforum;

import org.eforum.mail.Email;
import org.eforum.mail.MailEntity;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import sun.awt.AppContext;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

@SpringBootApplication
@EnableTransactionManagement(proxyTargetClass = true)
public class FrontApplication extends SpringBootServletInitializer {
	public static void main(String[] args) {
		SpringApplication.run(FrontApplication.class, args);
	}
	@Bean
    public Object testBean(PlatformTransactionManager platformTransactionManager){
        System.out.println(">>>>>>>>>>" + platformTransactionManager.getClass().getName());
        return new Object();
    }

    /**
     * 因为springboot没有web.xml文件，如果打成war包的话，该方法作用即为将springMVC绑定到web环境中(类似于在web.xml加入springMVC)
     * @param builder
     * @return
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(this.getClass());
    }
}