package com.star.guo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

	@Value("${server.port}")
	private String port;

	@GetMapping
	public String hello(String user) {
		return "hello "+user+" current port is:"+port;
	}

}
