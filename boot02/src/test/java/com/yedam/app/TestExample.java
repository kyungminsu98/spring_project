package com.yedam.app;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.yedam.app.aop.service.AaaService;

@SpringBootTest
public class TestExample {
	@Autowired
	AaaService aaaService;
	
	@Test
	public void aaaInsert() {
		aaaService.insert();
	}
}
