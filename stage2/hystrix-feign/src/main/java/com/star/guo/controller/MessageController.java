package com.star.guo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.star.guo.service.FeignService;

/**
 * 消息Controller,使用feign测试集群效果
 * 
 * @author Star.Guo
 *
 */
@RestController
public class MessageController {

	@Autowired
	FeignService feignService;

	/**
	 * Hello方法
	 * 
	 * @param user 用户名
	 * @return String
	 */
	@GetMapping
	public String hello(String user) {
		return feignService.hello(user);
	}

}
