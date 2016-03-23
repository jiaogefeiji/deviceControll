package com.cloud.amap.test;

import com.amap.service.impl.AmapCloudService;
import com.amap.service.impl.AmapCloudServiceImpl;

public class DeleteDevice {
	public static void main(String[] args) {
		 AmapCloudService amapCloudService = new AmapCloudServiceImpl();
		 String param =
		 "{\"tableid\":\"56ee656d7bbf197f399f1004\",\"ids\":\"3\"}";
		 String val = amapCloudService.deleteData(param);
		 System.out.println(val);
	}
}
