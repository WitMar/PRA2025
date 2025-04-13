package com.pracownia.spring;

import com.pracownia.spring.model.DataSet;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@SpringBootConfiguration
public class SpringAppConfig {
    @Bean
    public DataSet sellers() {
        return new DataSet();
    }

    @Bean
    public LocalValidatorFactoryBean validator() {
        return new LocalValidatorFactoryBean();
    }
}
