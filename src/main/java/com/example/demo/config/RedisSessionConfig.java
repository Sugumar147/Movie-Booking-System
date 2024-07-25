package com.example.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.web.http.CookieSerializer;
import org.springframework.session.web.http.DefaultCookieSerializer;

@Configuration
@EnableRedisHttpSession
public class RedisSessionConfig {

    @Value("${server.servlet.session.cookie.name}")
    private String sessionName;

    @Bean
    public LettuceConnectionFactory connectionFactory() {

        return new LettuceConnectionFactory();
    }

    @Bean
    public CookieSerializer cookieSerializer() {

        DefaultCookieSerializer serializer = new DefaultCookieSerializer();
        serializer.setCookieName("Dev-"+sessionName);
        serializer.setCookiePath("/");
        serializer.setDomainNamePattern("^.+?\\.(\\w+\\.[a-z]+)$");
        return serializer;
    }
}
