package com.demo.ui.client.controller;


import com.demo.ui.client.client.HelloClient;
import com.demo.ui.client.service.HelloService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wwchang
 * @date 2017/11/29 19:02
 **/
@Api(tags = {"Hello"})
@RestController
public class HelloController {
	//此种方式不能实时刷新,
	@Value("${from}")
	private String from;

	@Autowired
	private HelloClient helloClient;

	//保证实时数据
	@Autowired
	private Environment env;

	@Autowired
	private HelloService helloService;

	@GetMapping("/hello")
	public String hello(){

		return "你好 !";
	}
	@ApiOperation("测试Config")
	@GetMapping("/from")
	public String from(){

		return "from :" +from + " Environment : " + env.getProperty("from","undefined");
	}

	@ApiOperation("测试Ms")
	@GetMapping("/msHello")
	public String mshHello(String name){

		String hello = helloService.hello(name);
		return hello ;
	}
}
