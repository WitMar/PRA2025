package com.pracownia.spring;


import com.pracownia.spring.model.DataSet;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    @Bean
    public DataSet sellers() {
        return new DataSet();
    }
}
