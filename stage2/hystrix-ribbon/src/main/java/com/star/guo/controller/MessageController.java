package com.star.guo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.star.guo.service.HelloService;

@RestController
public class MessageController {

	@Autowired
	HelloService helloService;

	@GetMapping
	public String hello(String user) {
		return helloService.hello(user);
	}

}
