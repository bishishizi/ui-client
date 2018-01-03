package com.demo.ui.client.config;

import com.demo.ui.client.util.LogUtil;
import com.demo.ui.client.service.Oauth2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wwchang
 * @date 2017/11/30 14:06
 **/
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
	@Autowired
	private Oauth2Service oauth2Service;
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		LogUtil.info("CustomAuthenticationProvider");
		OAuth2AccessToken oAuth2AccessToken = oauth2Service.getToken();
		//可以自定义验证信息
		String name = authentication.getName();
		String password = (String) authentication.getCredentials();
		//赋予当前用户的不同权限
		List<GrantedAuthority> roles = new ArrayList<>();
		roles.add(new SimpleGrantedAuthority("user"));
		//生成的资源会存放在SecurityContentHolder中,可以扩展
		return new UsernamePasswordAuthenticationToken(name, password, roles);
	}

	@Override
	public boolean supports(Class<?> authentication) {
		//返回值是false无效
		return true;
	}
}
