package com.crypt.app.crypt;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

import static org.springframework.boot.SpringApplication.*;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, SecurityAutoConfiguration.class})
public class CryptApplication {

	public static void main(String[] args) {
		run(CryptApplication.class, args);
	}

}
