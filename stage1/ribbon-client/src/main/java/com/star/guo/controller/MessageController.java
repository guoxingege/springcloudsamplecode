package com.star.guo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * 消息Controller,使用ribbon测试集群效果
 * @author Star.Guo
 *
 */
@RestController
public class MessageController {

	@Autowired
	RestTemplate restTemplate;
	/**
	 * 调用的服务名
	 */
	private static final String serviceName = "EUREKA-CLIENT";

	/**
	 * Hello方法
	 * @param user 用户名
	 * @return String
	 */
	@GetMapping
	public String hello(String user) {
		return restTemplate.getForObject("http://" + serviceName + "?user=" + user, String.class);
	}

}
