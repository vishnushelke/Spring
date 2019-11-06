package com.bridgelabz.fundoo.user.configuration;

import org.springframework.context.annotation.Configuration;

@Configuration
public class RedisConf {

//	/**
//	 * purpose: this method creates a bean of jedisConnectionFactory. We need to have a connection 
//	 * 			factory to connect to the redis server
//	 * @return jedisConnectionFactory 
//	 */
//	@Bean
//	JedisConnectionFactory jedisConnectionFactory() {
//		return new JedisConnectionFactory();
//	}
//
//	/**
//	 * purpose: this method creates a bean of redisTemplate
//	 * @return redisTemplate
//	 */
//	@Bean
//	RedisTemplate<String, Note> redisTemplate() {
//		RedisTemplate<String, Note> redisTemplate = new RedisTemplate<>();
//		redisTemplate.setConnectionFactory(jedisConnectionFactory());
//		return redisTemplate;
//	}
}
