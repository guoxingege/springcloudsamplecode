package com.star.guo.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.star.guo.service.impl.FeignHystrixFallbackImpl;

@FeignClient(value = "EUREKA-CLIENT",fallback=FeignHystrixFallbackImpl.class)
public interface FeignService {

	/**
	 * hello方法
	 * 
	 * 本例子未使用其他的路径，如果使用的有配置就行如@GetMapping(value = "/hello")
	 * 
	 * @param user 用户名称
	 * @return String
	 */
	@GetMapping
	String hello(@RequestParam(value = "user") String user);
}
