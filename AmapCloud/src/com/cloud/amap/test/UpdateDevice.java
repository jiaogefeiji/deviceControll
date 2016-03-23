package com.cloud.amap.test;

import com.amap.service.impl.AmapCloudService;
import com.amap.service.impl.AmapCloudServiceImpl;

public class UpdateDevice {
	public static void main(String[] args) {
		AmapCloudService amapCloudService = new AmapCloudServiceImpl();
		 String param =
		 "{\"tableid\":\"56ee656d7bbf197f399f1004\",\"data\":{\"_id\":\"3\",\"_name\":\"设备一号\",\"_location\":\""+116.392442+","+ 39.89717+"\",\"coordtype\":\"gps\",\"_address\":\"西土城\",\"type\":\""+1+"\"}}";
		 String val = amapCloudService.updateData(param);
		 System.out.println(val);
	}
}
