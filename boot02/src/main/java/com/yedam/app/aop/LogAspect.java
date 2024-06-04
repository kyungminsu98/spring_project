package com.yedam.app.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect // AOP의 설정, 어드바이스(횡단관심) + 포인트컷 + 위빙
@Component
public class LogAspect {
	// 포인트컷 : 조인포인트 중에서 Advice(횡단관심)이 적용될 메소드 필터
	@Pointcut("within(com.yedam.app.emp.service.impl.*)")
	public void allPointCut() {
		
	}
	// 위빙 : 포인트컷 + 타이밍 + Advice(횡단관심)
	@Before("allPointCut()")
	public void beforAdvice(JoinPoint joinPoint) {
		String signaterStr = joinPoint.getSignature().toString();
		Object[] args = joinPoint.getArgs();
		System.out.println("############ 실행 : " + signaterStr);
		System.out.println("parameter list : " + args.toString());
	}
	@Around("allPointCut()")
	public Object logger(ProceedingJoinPoint joinPoint) throws Throwable {
		String signaterStr = joinPoint.getSignature().toString();
		System.out.println("시작 : " + signaterStr);
		// 공통기능
		System.out.println("핵심 기능 전 실행 : " + System.currentTimeMillis());
		try{
			// 비즈니스 메소드를 실행
			Object obj = joinPoint.proceed();
			return obj;
		}finally {
			System.out.println("핵심 기능 후 실행 :" + System.currentTimeMillis());
			System.out.println("끝 : " + signaterStr);
		}
	}
}
