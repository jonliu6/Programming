package org.freecode.demo.aop;

import java.util.Calendar;
import java.util.Date;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class TimestampLog {
	
	@Pointcut("execution(* org.freecode.demo.model.KnowledgeBaseDAO.findKnowledgeByKeyword(..))")
	public void genericPointcut() {
		
	}

	// @Before("execution(* org.freecode.demo.model.KnowledgeBaseDAO.findKnowledgeByKeyword(..))")
	@Before("genericPointcut()")
	public void printOutSystemTime(JoinPoint jp) throws Exception {
		Boolean canDo = true;
		Calendar c = Calendar.getInstance();
		if (c != null) {
			System.out.println("Current Time ==> " +
		                       c.get(Calendar.HOUR_OF_DAY) + ":" + 
		                       c.get(Calendar.MINUTE) + ":" +
					           c.get(Calendar.SECOND) + "." + 
		                       c.get(Calendar.MILLISECOND));
		}
		else {
			canDo = false;
		}
		
		if (canDo) {
			StringBuilder sb = new StringBuilder();
			sb.append("JoinPoint kind: " + jp.getKind()).append("\n");
			sb.append("JoinPoint Static Part: " + jp.getStaticPart()).append("\n");
			Object[] args = jp.getArgs();
			if (args != null) {
				for (int i=0, len=args.length; i<len; ++i) {
					sb.append("Argument " + i + ": " + args[i]).append("\n");
				}
			}
			sb.append("JointPoint Target: " + jp.getTarget());
			System.out.println(sb.toString());
		}
		else {
			// when exception thrown in before method, the actual method will not be called.
			throw new RuntimeException("CANNOT print system timestamp.");
		}
	}
	
	@AfterReturning(pointcut="execution(* org.freecode.demo.model.KnowledgeBaseDAO.findKnowledgeByKeyword(..))", returning="returnedResult")
	public void printCompletionMessage(Object returnedResult) {
		System.out.println("Completed and returned (" + returnedResult.getClass().getName() + "): " + returnedResult.toString() + ".");
	}
	
	@Around("execution(void org.freecode.demo.model.KnowledgeBaseDAO.addKnowledgePoint(..))")
	public void printBeforeAfterExecution(ProceedingJoinPoint pjp) throws Throwable {
		System.out.println("Before execution" + new Date());
		try {
		    pjp.proceed(); // invoke the match method call - addKnowledgePoint
		}
		catch (Exception ex) {
			System.out.println("Exception caught: " + ex.getMessage());
		}
		System.out.println("After execution" + new Date());
	}
}
