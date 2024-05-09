package com.example.testweb.config;

import io.lettuce.core.ClientOptions;
import io.lettuce.core.ReadFrom;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

@Configuration
public class RedisConfig {
    @Bean
    public RedisTemplate<String, ?> redisTemplate(@Value("${redis.port}") Integer port) {
        RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();
        configuration.setPort(port);

        LettuceClientConfiguration clientConfigurationbuild = LettuceClientConfiguration.builder()
            .clientName("test-redis")
            .commandTimeout(Duration.ofSeconds(3))
            .readFrom(ReadFrom.ANY)
            .clientOptions(ClientOptions.builder()
                .autoReconnect(true)
                .requestQueueSize(30)
                .build())
            .build();

        LettuceConnectionFactory connectionFactory = new LettuceConnectionFactory(configuration, clientConfigurationbuild);
        connectionFactory.afterPropertiesSet();

        RedisTemplate<String, ?> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(connectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        redisTemplate.afterPropertiesSet();

        return redisTemplate;
    }
}
