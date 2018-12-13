package com.star.guo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class HelloService {
	/**
	 * 调用的服务名
	 */
	private static final String serviceName = "EUREKA-CLIENT";
	@Autowired
	RestTemplate restTemplate;

	@HystrixCommand(fallbackMethod = "helloFallback")
	public String hello(String user) {
		return restTemplate.getForObject("http://" + serviceName + "?user=" + user, String.class);
	}

	public String helloFallback(String user) {
		return "user传入" + user + "时，发生了异常";
	}

}
