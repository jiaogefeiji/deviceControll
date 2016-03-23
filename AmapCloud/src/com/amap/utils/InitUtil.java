/**   
 * ��һ�仰�������ļ���ʲô.
 * @title MongoDBUtil.java
 * @package com.collection.mongodb.test
 * @author ��˧   
 * @update 2015-11-12 ����11:32:27  
 */
package com.amap.utils;

import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

/**
 * 
 * @author
 * @date 2015-5-29 ����11:49:49
 * @version 0.0.0
 * @Copyright (c)1997-2015 NavInfo Co.Ltd. All Rights Reserved.
 */
public enum InitUtil {

	/**
	 * ����һ��ö�ٵ�Ԫ�أ�����������һ��ʵ��
	 */
	instance;

	private String createtableurl;
	private String createdataurl;
	private String batchcreateurl;
	private String updatedataurl;
	private String deletedataurl;
	private String localdatasearchurl;
	private String arounddatasearchurl;
	private String polygondatasearchurl;
	private String iddatasearchurl;
	private String paramlistdataurl;

	static {
		System.out
				.println("===============InitUtil��ʼ��========================");
		CompositeConfiguration config = new CompositeConfiguration();
		try {
			config.addConfiguration(new PropertiesConfiguration(
					"init.properties"));
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
		// �������ļ��л�ȡ����ֵ
		String createtable = config.getString("amap.cloud.createtable");
		String createdata = config.getString("amap.cloud.createdata");
		String batchcreate = config.getString("amap.cloud.batchcreate");
		String updatedata = config.getString("amap.cloud.updatedata");
		String deletedata = config.getString("amap.cloud.deletedata");
		String localdatasearch = config.getString("amap.cloud.localdatasearch");
		String arounddatasearch = config
				.getString("amap.cloud.arounddatasearch");
		String polygondatasearch = config
				.getString("amap.cloud.polygondatasearch");
		String iddatasearch = config.getString("amap.cloud.iddatasearch");
		String paramlistdata = config.getString("amap.cloud.paramlistdata");

		instance.createtableurl = createtable;
		instance.createdataurl = createdata;
		instance.batchcreateurl = batchcreate;
		instance.updatedataurl = updatedata;
		instance.deletedataurl = deletedata;
		instance.localdatasearchurl = localdatasearch;
		instance.arounddatasearchurl = arounddatasearch;
		instance.polygondatasearchurl = polygondatasearch;
		instance.iddatasearchurl = iddatasearch;
		instance.paramlistdataurl = paramlistdata;
	}

	public static void main(String[] args) {
		// System.out.println(InitUtil.instance.getServiceLocation()+"----"+InitUtil.instance.getPayPath());
	}

	public String getCreatetableurl() {
		return createtableurl;
	}

	public void setCreatetableurl(String createtableurl) {
		this.createtableurl = createtableurl;
	}

	public String getCreatedataurl() {
		return createdataurl;
	}

	public void setCreatedataurl(String createdataurl) {
		this.createdataurl = createdataurl;
	}

	public String getBatchcreateurl() {
		return batchcreateurl;
	}

	public void setBatchcreateurl(String batchcreateurl) {
		this.batchcreateurl = batchcreateurl;
	}

	public String getUpdatedataurl() {
		return updatedataurl;
	}

	public void setUpdatedataurl(String updatedataurl) {
		this.updatedataurl = updatedataurl;
	}

	public String getDeletedataurl() {
		return deletedataurl;
	}

	public void setDeletedataurl(String deletedataurl) {
		this.deletedataurl = deletedataurl;
	}

	public String getLocaldatasearchurl() {
		return localdatasearchurl;
	}

	public void setLocaldatasearchurl(String localdatasearchurl) {
		this.localdatasearchurl = localdatasearchurl;
	}

	public String getArounddatasearchurl() {
		return arounddatasearchurl;
	}

	public void setArounddatasearchurl(String arounddatasearchurl) {
		this.arounddatasearchurl = arounddatasearchurl;
	}

	public String getPolygondatasearchurl() {
		return polygondatasearchurl;
	}

	public void setPolygondatasearchurl(String polygondatasearchurl) {
		this.polygondatasearchurl = polygondatasearchurl;
	}

	public String getIddatasearchurl() {
		return iddatasearchurl;
	}

	public void setIddatasearchurl(String iddatasearchurl) {
		this.iddatasearchurl = iddatasearchurl;
	}

	public String getParamlistdataurl() {
		return paramlistdataurl;
	}

	public void setParamlistdataurl(String paramlistdataurl) {
		this.paramlistdataurl = paramlistdataurl;
	}

}