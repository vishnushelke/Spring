/******************************************************************************
*  Purpose: This is a configuration class of redis.
*  @author  Vishnu Shelke
*  @version 1.0
*  @since   05-11-2019
*
******************************************************************************/
package com.bridgelabz.fundoo.user.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import com.bridgelabz.fundoo.user.note.model.Note;

@Configuration
public class RedisConfig {

	/**
	 * purpose: this method creates a bean of jedisConnectionFactory. We need to have a connection 
	 * 			factory to connect to the redis server
	 * @return jedisConnectionFactory 
	 */
	@Bean
	JedisConnectionFactory jedisConnectionFactory() {
		return new JedisConnectionFactory();
	}

	/**
	 * purpose: this method creates a bean of redisTemplate
	 * @return redisTemplate
	 */
	@Bean
	RedisTemplate<String, Note> redisTemplate() {
		RedisTemplate<String, Note> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(jedisConnectionFactory());
		return redisTemplate;
	}
}
