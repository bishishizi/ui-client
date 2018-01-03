package com.demo.ui.client.service;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.oauth2.client.token.DefaultAccessTokenRequest;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsAccessTokenProvider;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.stereotype.Service;

/**
 * @author wwchang
 * @date 2017/12/1 16:48
 **/
@Service
public class Oauth2Service {
	@Value("${security.oauth2.client.user-authorization-uri}")
	private String authorizationUrl;
	@Value("${security.oauth2.client.access-token-uri}")
	private String tokenUrl;
	@Value("${security.oauth2.client.client-id}")
	private String clientId;
	@Value("${security.oauth2.client.client-secret}")
	private String clientSecret;

	@Autowired
	RedisTemplate redisTemplate;

	private OAuth2AccessToken oAuth2AccessToken;

	/**
	 * 客户端模式
	 */
	public  OAuth2AccessToken getToken(){
		ClientCredentialsAccessTokenProvider credentialsAccessTokenProvider = new ClientCredentialsAccessTokenProvider();
		//一些必要配置
		ClientCredentialsResourceDetails resourceDetails = new ClientCredentialsResourceDetails();
		resourceDetails.setAccessTokenUri(tokenUrl);
		resourceDetails.setClientId(clientId);
		resourceDetails.setClientSecret(clientSecret);
		//获取token
		oAuth2AccessToken = credentialsAccessTokenProvider.obtainAccessToken(resourceDetails, new DefaultAccessTokenRequest());
		String jsonString = JSON.toJSONString(oAuth2AccessToken.getValue());
		redisTemplate.opsForValue().set("access_token",jsonString);
 		return oAuth2AccessToken;
	}

	/**
	 * 密码模式,暂无
	 */
	public void  getCredentialToken(){
	}

}
