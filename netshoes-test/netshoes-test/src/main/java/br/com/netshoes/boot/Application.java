package br.com.netshoes.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;



@SpringBootApplication
@ComponentScan(basePackages={ "br.com.netshoes.service", "br.com.netshoes.web" })
@EntityScan(basePackages="br.com.netshoes.domain")
@EnableJpaRepositories(basePackages="br.com.netshoes.repository")
@EnableAutoConfiguration
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Bean
	public MethodValidationPostProcessor methodValidationPostProcessor() {
	    final MethodValidationPostProcessor methodValidationPostProcessor =
	    		new MethodValidationPostProcessor();
	    methodValidationPostProcessor.setValidator(validator());
	    return methodValidationPostProcessor;
	}

	@Bean
	public LocalValidatorFactoryBean validator() {
	    final LocalValidatorFactoryBean localValidatorFactoryBean = 
	    		new LocalValidatorFactoryBean();
	    return localValidatorFactoryBean;
	}

}
