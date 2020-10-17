package com.example.demo;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@EnableAutoConfiguration
@RequestMapping(value = "/customer")
public class ScrapCtrl {

	
	@Autowired
	ScrapRepository scrapRepository;
	/*
	@PostMapping("/")
	public @ResponseBody List<Customer> createCustomer(@RequestBody Map<String,String> param){
		String name = param.get("name");
		String phone = param.get("phone");
		Customer customer = Customer.builder().name(name).phone(phone).build();
		customerRepository.save(customer);
		
		return customerRepository.findAll();
	}
	
	*/
}
