package com.bridgelabz.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class LoggingAspect {

	
	//joinpoint gives you info which method is called
//	toString=gives name of methos in string
//	getTarget=gives object of method being called
	@Before("allCircleMethod()")
	public void loggingAdvice(JoinPoint joinpoint) {
		System.out.println(joinpoint.getTarget());
	}

//	@Before("allCircleMethod()")
//	public void SecondAdvice() {
//		System.out.println("Advice run. Second Get method called");
//	}

	
	//for all getter methods
	@Pointcut("execution(* get*(..))")
	public void allGetter() {
	}
	
//	//for all methods of circle
//	@Pointcut("execution(* *com.bridgelabz.model.Circle.*(..))")
//	public void circleAdvice(){}
	
	
	//within the circle methods only
	@Pointcut("within(com.bridgelabz.model.Circle)")
	public void allCircleMethod() {}
	

}
