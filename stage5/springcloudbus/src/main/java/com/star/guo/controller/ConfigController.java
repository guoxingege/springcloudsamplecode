package com.star.guo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class ConfigController {

	@Value("${starguo2}")
	String starguo2;

	@RequestMapping(value = "/readConfig")
	public String readConfig() {
		return starguo2;
		//http://localhost:8011/readConfig
	}
}
