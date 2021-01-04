package com.hzp.web.core;

public interface ServletRequest {
	/**
	 * 解析请求方法
	 */
	public void parse();
	/**
	 * 获取请求参数方法
	 * @param key
	 * @return
	 */
	public String getParameter(String key);
	/**
	 * 获取请求方式的方法
	 * @return
	 */
	public String getMethod();
	/**
	 * 获取请求地址的方法
	 * @return
	 */
	public String getUrl();
}
