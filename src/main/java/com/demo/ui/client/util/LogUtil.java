package com.demo.ui.client.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author wwchang
 * @date 2017/11/29 19:55
 **/
public class LogUtil {
	public static void info(String msg){
		Logger logger = LoggerFactory.getLogger(LogUtil.class);
		logger.info(msg);
	}
}
