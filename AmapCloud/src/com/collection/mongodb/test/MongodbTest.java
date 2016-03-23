package com.collection.mongodb.test;

import java.util.Map;
import java.util.regex.Pattern;

import com.amap.service.impl.AmapCloudService;
import com.amap.service.impl.AmapCloudServiceImpl;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.Mongo;
import com.sinosoft.framework.util.DateUtil;

public class MongodbTest {

	public static void main(String[] args) {
		//ʵ��Mongo��������27017�˿�
        Mongo mongo = new Mongo("101.200.89.208", 49239);
      //������Ϊyourdb����ݿ⣬������ݿⲻ���ڵĻ���mongodb���Զ�����
        DB db = mongo.getDB("jiaogefeiji");
      //��Mongodb�л����ΪyourColleection����ݼ��ϣ�������ݼ��ϲ����ڣ�Mongodb��Ϊ���½���
        DBCollection collection = db.getCollection("userinfo_geo");
//     // ʹ��BasicDBObject���󴴽�һ��mongodb��document,�����踳ֵ��
//        BasicDBObject document = new BasicDBObject();
//        document.put("EVALUATE", 1001);
        String date = DateUtil.getSysDateStr("yyyy-MM-dd");
      //ģ��ƥ��
        Pattern pattern = Pattern.compile("^.*"+date+".*$", Pattern.CASE_INSENSITIVE);
        // ����Ҫ��ѯ��document
        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.put("addtime", pattern);
        // ʹ��collection��find��������document
        DBCursor cursor = collection.find(searchQuery);
        
    	AmapCloudService amapCloudService = new AmapCloudServiceImpl();
    	
    	
//    	//������
//		 String param = "{\"name\":\""+date+"�û���¼Map\"}";
//		 String tableid = amapCloudService.createTable(param);
        //ѭ��������
        while (cursor.hasNext()) {
        			System.out.println(cursor.next());
        			Map<String, String> valmap = cursor.next().toMap();
        			 //�������
        			 String mapdata =
        			 "{\"tableid\":\"568e0c20305a2a31f62103b7\",\"data\":{\"_name\":\"u\",\"_location\":\""+valmap.get("longitude").toString()+","+valmap.get("latitude").toString()+"\",\"coordtype\":\"gps\",\"_address\":\"x\"}}";
        			 String val = amapCloudService.createTableData(mapdata);
        			 System.out.println(val);
        }
        System.out.println("Done"); 
	}
}
