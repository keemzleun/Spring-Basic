package com.beyond.basic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// 해당 어노테이션을 통해 Compoenent Scanning을 수행
// BasicApplication(메인 프로그램)과 동등하거나 하위에 있는 프로그램에 포함된 어노테이션만 스캔 가능
@SpringBootApplication
public class BasicApplication {

	public static void main(String[] args) {
		SpringApplication.run(BasicApplication.class, args);
	}

}
