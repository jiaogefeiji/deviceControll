package com.cloud.amap.test;

import com.amap.service.impl.AmapCloudService;
import com.amap.service.impl.AmapCloudServiceImpl;

public class CreateDevice {
	
	public static void main(String[] args) {
		AmapCloudService amapCloudService = new AmapCloudServiceImpl();
		 String param =
		 "{\"tableid\":\"56ee656d7bbf197f399f1004\",\"data\":{\"_name\":\"x\",\"_location\":\""+116.392442+","+ 39.89717+"\",\"coordtype\":\"gps\",\"_address\":\""+123131+"\",\"type\":\""+1+"\"}}";
		 String val = amapCloudService.createTableData(param);
		 System.out.println(val);
	}
}
