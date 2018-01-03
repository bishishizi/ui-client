package com.demo.ui.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.CharacterEncodingFilter;

/**
 * @author wwchang
 * @date 2017/11/29 17:34
 */
@EnableEurekaClient
@SpringBootApplication
@EnableFeignClients
public class UiClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(UiClientApplication.class, args);
	}

	/**
	 * 设置字符集
	 */
	@Bean
	public CharacterEncodingFilter characterEncodingFilter(){
		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter("UTF-8");
		return characterEncodingFilter;
	}
}
