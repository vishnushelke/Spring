package com.bridgelabz.springbootstarter.hello;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController//this is the class which is dealing with http requests
public class HelloController {

	@RequestMapping("/hello")//whenever request comes with /hello,call this method and return a string whatever i am returning
	public String sayHi()
	{
		return "Hi";
		
	}
}
