
package com.nus;

import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import jakarta.annotation.PostConstruct;

/**
 * @Author: SanjeevKumar<br>
 * @Date: 17-Jan-2025<br>
 * @Time: 10:11:05 pm<br>
 * @Objective: <br>
 */
@SpringBootApplication
public class ApexMainApplication {

	@Value("${application.timezone}")
    private String applicationTimeZone;
	
	public static void main(String[] args) {		
		SpringApplication.run(ApexMainApplication.class, args);

	}
	@PostConstruct
    public void executeAfterMain() {
        TimeZone.setDefault(TimeZone.getTimeZone(applicationTimeZone));     
    }
}
