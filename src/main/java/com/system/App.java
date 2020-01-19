package com.system;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App {
	private static Logger logger = LoggerFactory.getLogger(App.class);

	public static void main(String[] args) {
		logger.info("将要启动定时器---------");
		try {
			new ClassPathXmlApplicationContext(new String[]{"quartz_timer.xml"});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
