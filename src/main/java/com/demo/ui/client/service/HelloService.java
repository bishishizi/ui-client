package com.demo.ui.client.service;

import com.demo.ui.client.client.HelloClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 测试service层
 * @author wwchang
 * @date 2017/12/14 14:37
 **/
@Service
public class HelloService {
	@Autowired
	private HelloClient helloClient;

	public String hello(String name){
		return  helloClient.msHello(name);
	}
}
