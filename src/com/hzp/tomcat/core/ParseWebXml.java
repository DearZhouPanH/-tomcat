package com.hzp.tomcat.core;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;




public class ParseWebXml {
	private static Map<String ,String> map = new HashMap<String ,String>();
	
	public ParseWebXml() {
		init();
	}

	/**
	 * 初始化
	 */
	@SuppressWarnings("unchecked")
	private void init() {
		SAXReader reader =  new SAXReader();
		Document doc = null;
	
		try {
			doc = reader.read(this.getClass().getClassLoader().getResourceAsStream("web.xml"));
			List<Element> mines = doc.selectNodes("//mine-mappping");
			for(Element el:mines) {
				map.put(el.selectSingleNode("extension").getText().trim(),el.selectSingleNode("mime-type").getText().trim());			
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public Map<String ,String> getMap(){
		return map;
	}

	public static String getContentType(String key) {
		// TODO Auto-generated method stub
		return map.getOrDefault(key, null);
	}
}
