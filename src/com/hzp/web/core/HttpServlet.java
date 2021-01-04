package com.hzp.web.core;

import com.hzp.tomcat.core.ConstantInfo;

public class HttpServlet implements Servlet{
	
	

	public void doGet(ServletRequest request, ServletResponse response) {
		// TODO Auto-generated method stub
		
	}
	
	public void doPost(ServletRequest request, ServletResponse response) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void service(ServletRequest request, ServletResponse response) {
		switch (request.getMethod()){
			case ConstantInfo.REQUEST_METHOD_GET: doGet(request,response);break;
			case ConstantInfo.REQUEST_METHOD_POST: doPost(request, response);break;
		}
	}

}
