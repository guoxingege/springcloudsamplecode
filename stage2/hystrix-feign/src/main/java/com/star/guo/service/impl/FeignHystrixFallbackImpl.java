package com.star.guo.service.impl;

import org.springframework.stereotype.Service;

import com.star.guo.service.FeignService;

@Service
public class FeignHystrixFallbackImpl implements FeignService{

	@Override
	public String hello(String user) {
		// TODO Auto-generated method stub
		return "user 传入"+user+"时发生了异常 By Feign";
	}

}
