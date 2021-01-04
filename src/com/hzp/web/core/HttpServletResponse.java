package com.hzp.web.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import com.hzp.tomcat.core.ConstantInfo;
import com.hzp.tomcat.core.ParseWebXml;
import com.hzp.tomcat.util.StringUtil;
import com.hzp.web.core.ServletResponse;

public class HttpServletResponse implements ServletResponse {
	private OutputStream os = null;
	private String basePath = ConstantInfo.BASE_PATH;
	private String projectName;
	
	

	public HttpServletResponse(OutputStream os, String projectName2) {
		this.os = os;
		this.projectName ="/" + projectName;
	}

	@Override
	public void sendReadirect(String url) {
		if(StringUtil.checkNull(url)) {
			//报错
			error404(url);
			return;
		}
		
		// /snacknet/login
		if(!url.startsWith(projectName)) {
			send302(projectName + "/" +url);
			return;
		}
		
		if(url.startsWith("/") & url.indexOf("/") == url.lastIndexOf("/")) {// /snacknet
			send302(url);
			return;
		}else {// /snacknet/index.html   /snacknet/   /snacknet/back/index.html
			if(url.endsWith("/")) {//说明没有指定具体资源
				String defaultPath = ConstantInfo.DEFAULT_RESOURCE;//获取默认页面
				
				File fl = new File(basePath, url.substring(1).replace("/", "\\") + defaultPath);
				if(!fl.exists() || !fl.isFile()) {
					error404(url);
					return;
				}
				send200(readFile(fl),url.substring(url.lastIndexOf(".")+1).toLowerCase());
				return;
			}
			File fl = new File(basePath, url.substring(1).replace("/", "\\"));
			
			if(!fl.exists() || !fl.isFile()) {
				//报错
				error404(url);
				return;
			}
			send200(readFile(fl),url.substring(url.lastIndexOf(".")+1).toLowerCase());
		}
		
		
		
		
		
	}

	/**
	 * 发送
	 * @param bt 要发送的数据
	 * @param extension 扩展名
	 */
	private void send200(byte[] bt, String extension) {
		String contentType = "text/html;charset = utf-8";
		String type = ParseWebXml.getContentType(extension);
		if(!StringUtil.checkNull(type)) {
			contentType = type;
		}
		try {
			String responseHeader = "HTTP/1.1 200 OK\r\nContent-Type: " + contentType + "\r\nContent-Length: " + bt.length + "\r\n\r\n";
			os.write(responseHeader.getBytes());
			os.write(bt);
			os.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(os != null) {
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 读取指定文件
	 * @param fl
	 * @return
	 */
	private byte[] readFile(File fl) {
		byte[] bt = null;
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(fl);
			bt = new byte[fis.available()];
			fis.read(bt);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return bt;
	}

	private void send302(String url) {
		try {		
			String responseHeader = "HTTP/1.1 302 Moved Temporarily\r\nContent-Type: text/html;charse=utf-8\r\nLocation: "+ url  + "\r\n\r\n";
			os.write(responseHeader.getBytes());
			os.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(os != null) {
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}

	/**
	 * 404报错
	 * @param url
	 */
	private void error404(String url) {
		try {
			String data  = "<h1>HTTP Status 4040 - " + url + "</h1>";
			String responseHeader = "HTTP/1.1 404 File Not Found\r\nContent-Type: text/html;charset=utf-8\r\nContent-Length:" +data.length() + "\r\n\r\n";
			os.write(responseHeader.getBytes());
			os.write(data.getBytes());
			os.close();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(os != null) {
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}

	@Override
	public PrintWriter getWriter() throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

}
