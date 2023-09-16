package com.example.blogrestpoints;

import com.example.blogrestpoints.repositry.UserRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BlogRestPointsApplicationTests {

	@Autowired
	private UserRepo userRepo;

	@Test
	void contextLoads() {
	}

	@Test
	public void RespoTest() {

		String className = this.userRepo.getClass().getName();
		System.out.println(className);

	}

}
