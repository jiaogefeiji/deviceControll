package com.cloud.amap.test;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.amap.cloud.entity.MapData;
import com.amap.cloud.mapdata.service.AmapDataService;
import com.amap.cloud.mapdata.service.AmapDataServiceImpl;
import com.amap.service.impl.AmapCloudService;
import com.amap.service.impl.AmapCloudServiceImpl;
import com.amap.utils.HttpClientUtil;
import com.amap.utils.JsonUtil;


public class DataSave {
	public static void main(String[] args) {
		AmapCloudService amapCloudService = new AmapCloudServiceImpl();
		
		AmapDataService amapDataService  = new AmapDataServiceImpl();
		try {
			DataSave apiURL  = new DataSave();
			String result = HttpClientUtil.GET("http://ditu.amap.com/service/poiInfo?query_type=TQUERY&city=100000&keywords=%E6%B1%BD%E8%BD%A6%E7%AB%99&pagesize=20&pagenum=1&qii=true&cluster_state=5&need_utd=true&utd_sceneid=1000&div=PC1000&addr_poi_merge=true&is_classify=true");
			System.out.println(result);
			JSONObject jsonObject = JSONObject.fromObject(result);
			JSONArray array  = JSONArray.fromObject(jsonObject.get("data"));
			JSONObject dataobj = JSONObject.fromObject(array.get(0));
			JSONArray arraylist = JSONArray.fromObject(dataobj.get("list"));
			 //创建数据
//			 String param =
//			 "{\"tableid\":\"56ee656d7bbf197f399f1004\",\"data\":{\"_name\":\"驴\",\"_location\":\"116.34657461579138,39.975494268313135\",\"coordtype\":\"gps\",\"_address\":\"饺子\",\"uid\":\"a547708f-6d8c-4fe4-8e24-e6df917d235e\"}}";
//			 String val = amapCloudService.createTableData(param);
			for (int i = 0; i < arraylist.size(); i++) {
				JSONObject valobj = JSONObject.fromObject(arraylist.get(i));
				Map<String, String> valmap = JsonUtil.getMapFromJson(valobj.toString());
				JSONObject  localobj = JSONObject.fromObject(valmap.get("location"));
				if(localobj.containsKey("lng"))
				{
					String lng = localobj.getString("lng");
					String lat = localobj.getString("lat");
					String name  =  valmap.get("name");
					String keyword = "火车站";        //valmap.get("keyword");
					String ename = valmap.get("ename");
					String adcode = valmap.get("adcode");
					MapData data  = new MapData(); 
					data.setId(UUID.randomUUID()+"");
					data.setName(name);
					data.setX(lng);
					data.setY(lat);
					data.setTelephone(adcode);
					amapDataService.saveMapData(data);
//					 String param =
//					 "{\"tableid\":\"568dd5d5305a2a31f61dde59\",\"data\":{\"_name\":\""+name+"\",\"_location\":\""+lng+","+lat+"\",\"coordtype\":\"gps\",\"_address\":\""+keyword+"\",\"ename\":\""+ename+"\",\"adcode\":\""+adcode+"\"}}";
//					 String val = amapCloudService.createTableData(param);
//					 System.out.println(val);
				}
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
