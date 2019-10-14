package com.bridgelabz.aspects;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class LoggingAspect {

	@Before("execution(allGetter())")
	public void loggingAdvice() {
		System.out.println("Advice run. Get method called");
	}

	@Before("allGetter())")
	public void SecondAdvice() {
		System.out.println("Advice run. Get method called");
	}

	@Before("execution(* get*(..)")
	public void allGetter() {
	}

}
