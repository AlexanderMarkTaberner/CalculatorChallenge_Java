package com.nerdywoof.calculatorchallenge;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CalculatorChallengeApplication {
        
        private static final Logger log = LoggerFactory.getLogger(CalculatorChallengeApplication.class);
        
	public static void main(String[] args) {
		SpringApplication.run(CalculatorChallengeApplication.class);
	}
        
        

}
