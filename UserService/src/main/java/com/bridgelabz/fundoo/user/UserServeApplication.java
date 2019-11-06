/******************************************************************************
*  Purpose: This class is major class which contains main class
*
*  @author  Vishnu Shelke
*  @version 1.0
*  @since   23-10-2019
*
******************************************************************************/
package com.bridgelabz.fundoo.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import com.bridgelabz.fundoo.user.note.model.Note;
@EnableCaching
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class UserServeApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserServeApplication.class, args);
	}

}
