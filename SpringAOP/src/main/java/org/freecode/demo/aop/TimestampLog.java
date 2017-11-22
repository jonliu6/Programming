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
	/**
	 * Designators:
	 * execution(): match methods execution join points
	 * within(): match join points within certain types defined
	 * this(): match join points where bean reference is an instance of a given type
	 * target(): match join points where target object is an instance of a given type
	 * args(): match join points where methods have number and type of arguments defined
	 * @target(): match join points where class of executing object is annotated with specific annotation
	 * @args(): match join points where runtime type of actual arguments have types specified
	 * @within(): match join points within types have a specific annotation
	 * @annotation(): match join points where methods being executed with a specific annotation
	 * bean(): Spring AOP specific matching the bean ids/names
	 */
	
	// @Pointcut("execution(void org.freecode.demo.model.KnowledgeBaseDAO.*(*))") // match all methods returning void and with any parameters
	// @Pointcut("execution(* org.freecode.demo.model.KnowledgeBaseDAO.find*(..))") // match all return types and method name starting with find
	@Pointcut("execution(* org.freecode.demo.model.KnowledgeBaseDAO.findKnowledgeByKeyword(..))")
	public void genericPointcut() {
		
	}

	// @Before("execution(* org.freecode.demo.model.KnowledgeBaseDAO.findKnowledgeByKeyword(..))")
	// @Before("! genericPointcut()") // not apply to the genericPointcut pointcut but other methods
	// @Before("bean(knowledge*)") 
	// @Before("within(org.freecode.demo.model.*)") // all classes in the given hierachy
	// @Before("execution(* (@org.freecode.demo.aop.RandomAnnotation *).* (..) )") // match any classes with Random annotation and any parameters and any return types
	// @Before("@annotation(RandomAnnotation)") // match @RandomAnnotation
	// Also, you can use && or || to combine pointcuts, or use bean id
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
	
	@Before("@annotation(RandomAnnotation)")
	public void printBeforeAnnotation() {
		System.out.println("Before execution with annotation");
	}
}
