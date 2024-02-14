package com.ed.coindesk;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
@Slf4j
public class CurrencyApplication {
	public static void main(String[] args) {
		SpringApplication.run(CurrencyApplication.class, args);
	}
}
