package com.star.guo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConfigController {

	@Value("${starguo1}")
	String starguo1;
	@Value("${starguo2}")
	String starguo2;

	@RequestMapping(value = "/readConfig")
	public String readConfig() {
		return starguo1+":"+starguo2;
		//http://localhost:8009/readConfig
	}
}
