package com.demo.ui.client.filter;

import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author wwchang
 * @date 2017/11/30 16:25
 **/
public class IpSecurityFilter extends AbstractSecurityInterceptor implements Filter{
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		String remoteHost = request.getRemoteHost();

	}

	@Override
	public void destroy() {

	}

	@Override
	public Class<?> getSecureObjectClass() {
		return null;
	}

	@Override
	public SecurityMetadataSource obtainSecurityMetadataSource() {
		return null;
	}
}
