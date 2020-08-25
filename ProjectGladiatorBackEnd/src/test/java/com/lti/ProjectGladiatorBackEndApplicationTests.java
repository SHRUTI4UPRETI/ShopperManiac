package com.lti;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.CustomAutowireConfigurer;
import org.springframework.boot.test.context.SpringBootTest;

import com.lti.model.Customer;
import com.lti.repository.CustomerRepository;

@SpringBootTest
class ProjectGladiatorBackEndApplicationTests {
	
	@Autowired
	CustomerRepository customerRepo;
	
	@Test
	void updateCustomerPassword() {
			
		
		
	}

}
