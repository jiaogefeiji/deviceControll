package com.cloud.amap.test;

import java.util.UUID;

import com.amap.cloud.entity.MapData;
import com.amap.cloud.mapdata.service.AmapDataService;
import com.amap.cloud.mapdata.service.AmapDataServiceImpl;

public class DataTest {
	
	
	public static void main(String[] args) {
		AmapDataService amapDataService  = new AmapDataServiceImpl();
		MapData data  = new MapData(); 
		data.setId(UUID.randomUUID()+"");
		data.setName("中文中文中文中文的哦");
		data.setX("45678");
		data.setY("34567");
		data.setTelephone("1234567");
		amapDataService.saveMapData(data);
	}
}
