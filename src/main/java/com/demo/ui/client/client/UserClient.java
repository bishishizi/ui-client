package com.demo.ui.client.client;

import com.demo.model.vo.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
/**
 * User服务层
 * @author wwchang
 * @date 2017/12/14 15:25
 */
//@FeignClient(value = "${service.gateway.name}" ,url = "${service.gateway.url}",path = "/ms-client")
public interface UserClient {

	@RequestMapping(value = "/user/getUser" ,method = RequestMethod.GET)
	public User getUser();
}
