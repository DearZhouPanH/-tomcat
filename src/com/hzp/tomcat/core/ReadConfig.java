package com.hzp.tomcat.core;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 读取配置文件的类
 * @author Administrator
 *
 */
public class ReadConfig extends Properties{

	private static final long serialVersionUID = 1L;
	private static ReadConfig instance = new ReadConfig();
	
	private ReadConfig() {
		try (InputStream is = this.getClass().getClass().getResourceAsStream("web.properties")) {
			load(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static ReadConfig getInstance() {
		return instance;
	}

}
