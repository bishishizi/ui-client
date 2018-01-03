package com.demo.ui.client.config;

import com.demo.ui.client.util.LogUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.security.web.header.HeaderWriter;
import org.springframework.security.web.session.HttpSessionEventPublisher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 自定义加入Securitry spring管理不会验证security账号密码
 *
 * @author wwchang
 * @date 2017/11/29 19:09
 **/
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private AuthenticationProvider customAuthenticationProvider;

	/**
	 * 加入自定义AuthenticationProverter
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(customAuthenticationProvider);
	}

	/**
	 * 可以设置那些路径不走Security
	 */
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/refresh", "/bus/refresh", "/v1/openapi/json/versioncontroller/**","/swagger-ui.html", "/swagger-resources/**",
				"/v2/api-docs","/webjars/**","configuration/**");
	}

	/**
	 * 设置Security 相关
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().anyRequest().authenticated()
				//基于表达
				.and().formLogin()//.loginPage("/login/login")
				//基于httpBasic,浏览器弹框
				.and().httpBasic()
				.and().authorizeRequests().antMatchers("/**").permitAll()
//				.and().csrf().csrfTokenRepository(csrfTokenRepository())
				.and().headers().addHeaderWriter(new HeaderWriter() {
			@Override
			public void writeHeaders(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
				String requestURI = httpServletRequest.getRequestURI();
				LogUtil.info("uri :" + requestURI);
			}
		});
		http.csrf().disable();
	}

	/**
	 * 解决测试时的跨域问题
	 */
	private CsrfTokenRepository csrfTokenRepository() {
		HttpSessionCsrfTokenRepository csrfTokenRepository = new HttpSessionCsrfTokenRepository();
		csrfTokenRepository.setHeaderName("X-XSRF-TOKEN");
		return csrfTokenRepository;
	}

	@Bean
	public HttpSessionEventPublisher httpSessionEventPublisher() {
		return new HttpSessionEventPublisher();
	}
}
