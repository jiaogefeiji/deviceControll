package com.amap.configData;

/**
 * Title: AmapConfig.java Description: ��ͼ���ò���
 * 
 * @author ��˧��wes
 * @created 2016-1-6 ����11:59:20
 */

public class AmapConfig {

	public  String key;
	
	
	private static ThreadLocal tl = new ThreadLocal();

	public static AmapConfig inistance() {
		AmapConfig amapConfig = null;
		if (tl.get() == null) {
			amapConfig = new AmapConfig();
			tl.set(amapConfig);
		} else {
			Object object = tl.get();
			amapConfig = (AmapConfig) object;
		}
		return amapConfig;
	}

	public String getKey() {
		return key="074901aa627c7a4d22cc1151077268a2";
	}

	public void setKey(String key) {
		this.key = key;
	}
	
	
	
	
}
