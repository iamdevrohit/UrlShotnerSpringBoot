package com.urlshortner.demo.urlshortnerdemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import static com.urlshortner.demo.urlshortnerdemo.contant.Constant.REDIS_HOST;
import static com.urlshortner.demo.urlshortnerdemo.contant.Constant.REDIS_PORT;

@Configuration
@EnableRedisRepositories
public class RedisConfig {

    @Bean
    public JedisConnectionFactory connectionFactory(){

        RedisStandaloneConfiguration standaloneConfiguration = new RedisStandaloneConfiguration();
        standaloneConfiguration.setHostName(REDIS_HOST);
        standaloneConfiguration.setPort(REDIS_PORT);
        return new JedisConnectionFactory(standaloneConfiguration);

    }

    @Bean
    public RedisTemplate<String,Object> redisTemplate(){

        RedisTemplate<String,Object> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory());
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new StringRedisSerializer());
        template.setKeySerializer(new StringRedisSerializer());
        template.afterPropertiesSet();
        return template;

    }
}
