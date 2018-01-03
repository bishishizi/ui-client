package com.demo.ui.client.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

/**
 * Redis 序列化方式FastJson
 * @author wwchang
 * @date 2017/12/25 10:49
 **/
public class FastJsonSerializer implements RedisSerializer  {

	@Override
	public byte[] serialize(Object o) throws SerializationException {
		if (o == null){
			return new byte[0];
		}
		return JSON.toJSONBytes(o, SerializerFeature.DisableCircularReferenceDetect);
	}

	@Override
	public Object deserialize(byte[] bytes) throws SerializationException {
		return bytes == null?null : JSON.parseObject(bytes,Object.class, Feature.DisableCircularReferenceDetect);
	}
}
