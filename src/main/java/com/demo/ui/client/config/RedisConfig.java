package com.demo.ui.client.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

/**
 * Redis配置类
 *
 * @author wwchang
 * @date 2017/12/22 16:09
 **/
@Component
public class RedisConfig {

	@Autowired
	JedisConnectionFactory jedisConnectionFactory;

	@Bean
	public RedisTemplate<String,Object> redisTemplate(){
		RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(jedisConnectionFactory);
		//key的序列化方式统一,速度快等优点
		redisTemplate.setKeySerializer(stringRedisSerializer());
		redisTemplate.setHashKeySerializer(stringRedisSerializer());
		//value序列化方式选择
		redisTemplate.setValueSerializer(stringRedisSerializer());
		redisTemplate.setHashValueSerializer(stringRedisSerializer());
		return redisTemplate;
	}

	//初始化key序列化方式
	@Bean
	public StringRedisSerializer stringRedisSerializer(){
		return new StringRedisSerializer();
	}

	//value的序列化
	@Bean
	public FastJsonSerializer fastJsonSerializer(){
		return new FastJsonSerializer();
	}

}
