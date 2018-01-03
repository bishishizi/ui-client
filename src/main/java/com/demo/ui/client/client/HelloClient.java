package com.demo.ui.client.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *  Hello feign
 * @author wwchang
 * @date 2017/12/13 15:01
 */
@FeignClient("ms-gateway")
public interface HelloClient {

	@RequestMapping(value = "/ms-client/hello" ,method = RequestMethod.GET)
	public String msHello(@RequestParam("name") String name);
}
