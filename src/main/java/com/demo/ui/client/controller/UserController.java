package com.demo.ui.client.controller;

import com.demo.model.vo.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wwchang
 * @date 2017/11/30 21:31
 **/
@Api(tags = {"user"})
//@RestController
//@RequestMapping("/user/")
public class UserController {

	/**
	 * @description  从SecurityContent获取Principal
	 */
	@ApiOperation("获取userName")
	@GetMapping("getUserName")
	public String getUserName(){
		Authentication authentication =
				SecurityContextHolder.getContext().getAuthentication();
		String userName = (String) authentication.getPrincipal();
		return userName;
	}

	@ApiOperation("获取userName")
	@GetMapping("getUserName")
	public User getUser(){

		return null;
	}
}
