package com.basic.myspringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MySpringBoot3Application {

	public static void main(String[] args) {
		// 기본 설정 코드
//		SpringApplication.run(MySpringBoot3Application.class, args);
		
		// 직접 쳐본 코드
		SpringApplication application = new SpringApplication(MySpringBoot3Application.class);
		// WebApplication type 변경
		application.setWebApplicationType(WebApplicationType.SERVLET); // 실행을 해도 tomcat이 돌아가지 않음
		application.run(args);
	}

}
