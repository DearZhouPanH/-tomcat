package com.hzp.tomcat.core;

public interface ConstantInfo {

	 String BASE_PATH = ReadConfig.getInstance().getProperty("path");
	 String DEFAULT_RESOURCE = ReadConfig.getInstance().getProperty("default");//获取默认页面
	 String REQUEST_METHOD_GET =  ReadConfig.getInstance().getProperty("get");;
	 String REQUEST_METHOD_POST =  ReadConfig.getInstance().getProperty("post");;

}
