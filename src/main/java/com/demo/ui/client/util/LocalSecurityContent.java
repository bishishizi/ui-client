package com.demo.ui.client.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collection;

/**
 * @author wwchang
 * @date 2017/11/30 14:57
 **/
public class LocalSecurityContent {
	public static String getUserId() {
		Authentication authentication =
				SecurityContextHolder.getContext().getAuthentication();
		Object principal = authentication.getPrincipal();
		Object credentials = authentication.getCredentials();
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		return null;
	}
}
