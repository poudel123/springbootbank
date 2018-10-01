package com.capgemini.bankappcheck;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages= {"com.capgemini.bankappcheck"})
public class BankappcheckApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankappcheckApplication.class, args);
	}
}
