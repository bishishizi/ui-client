package com.demo.ui.client.controller;

import com.demo.ui.client.config.RedisConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author wwchang
 * @date 2017/11/30 10:58
 **/
@Controller
public class IndexController {

	@Autowired
	RedisTemplate redisTemplate;

	@GetMapping("/")
	public String login(){
		Object accessToken = redisTemplate.opsForValue().get("access_token");
		String value = (String) accessToken;
		System.out.println(value);
		return "index";
	}
}
