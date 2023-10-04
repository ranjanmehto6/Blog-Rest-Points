package com.example.blogrestpoints;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BlogRestPointsApplication  {

//	@Autowired
//	private PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(BlogRestPointsApplication.class, args);
	}

//	@Override
//	public void run(String... args) throws Exception {
//
//		System.out.println(this.passwordEncoder.encode("ravi"));
//
//	}
}
