package com.example.testweb.config;

import com.example.testweb.threadlocal.ThreadLocalDataInfoContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {
    @Bean
    public ThreadLocalDataInfoContext threadLocalUserInfoContext() {
        return new ThreadLocalDataInfoContext();
    }
}
