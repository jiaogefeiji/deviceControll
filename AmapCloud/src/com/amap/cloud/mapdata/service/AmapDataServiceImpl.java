package com.amap.cloud.mapdata.service;

import java.sql.SQLException;

import com.amap.cloud.entity.MapData;

import dbtools.DBOperate;

/**
 * 
 * @author wes
 *
 */
public class AmapDataServiceImpl implements AmapDataService {

	
	DBOperate dbOperate  = new DBOperate();
	/* (non-Javadoc)
	 * @see com.amap.cloud.mapdata.service.AmapDataService#saveMapData(java.lang.String)
	 */
	public void saveMapData(MapData data) {
		// TODO Auto-generated method stub
		try {
			String names = data.getName();
			StringBuffer buffer  =  new StringBuffer("INSERT INTO `mapdata` VALUES ('"+data.getId()+"','"+names+"', '"+data.getX()+"', '"+data.getY()+"', '"+data.getTelephone()+"');");
			dbOperate.Insert(buffer.toString(), null);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
