package com.ms.wm.cloudathon.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
@ComponentScan(basePackages = {"com.ms.wm.cloudathon"})
public class ParentConfiguration {

    /*@Bean
    public DataSource dataSource(){

    }*/

    @Bean
    public Map<String,String> mapObject(){
        Map<String,String> map = new HashMap<>();
        return map;
    }
}
