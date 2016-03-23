package com.amap.service.impl;

/**
 * 
 *   阿里云云图接口
 * @author wes
 *
 */
public interface AmapCloudService {
					  
					    /**     
					     * @discription 创建表
					     * @author 王帅—wes       
					     * @created 2016-1-6 上午11:52:35     
					     * @param param
					     * @return     
					     */
					public String createTable(String param);
					  
					    /**     
					     * @discription 在表中创建数据
					     * @author 王帅—wes       
					     * @created 2016-1-6 上午11:52:49     
					     * @param param
					     * @return     
					     */
					public String createTableData(String param);
					  
					    /**     
					     * @discription 批量创建数据
					     * @author 王帅—wes       
					     * @created 2016-1-6 上午11:53:10     
					     * @param param
					     * @return     
					     */
					public String batchCreateData(String param);
					  
					    /**     
					     * @discription 更新数据
					     * @author 王帅—wes       
					     * @created 2016-1-6 上午11:53:25     
					     * @param param
					     * @return     
					     */
					public String updateData(String param);
					  
					    /**     
					     * @discription 删除数据
					     * @author 王帅—wes       
					     * @created 2016-1-6 上午11:53:38     
					     * @param param
					     * @return     
					     */
					public String deleteData(String param);
					
					
					  
					    /**     
					     * @discription 检索全表数据
					     * @author 王帅—wes       
					     * @created 2016-1-6 下午3:03:04     
					     * @param param
					     * @return     
					     */
					public String queryAllData(String param);
					  
					    /**     
					     * @discription 本地查询数据
					     * @author 王帅—wes       
					     * @created 2016-1-6 上午11:53:46     
					     * @param param
					     * @return     
					     */
					public String localDataSearch(String param);
					  
					    /**     
					     * @discription 周边搜索数据
					     * @author 王帅—wes       
					     * @created 2016-1-6 上午11:53:59     
					     * @param param
					     * @return     
					     */
					public String aroundDataSearch(String param);
					  
					    /**     
					     * @discription 图形检索数据
					     * @author 王帅—wes       
					     * @created 2016-1-6 上午11:54:11     
					     * @param param
					     * @return     
					     */
					public String polygonDataSearch(String param);
					  
					    /**     
					     * @discription id搜索数据
					     * @author 王帅—wes       
					     * @created 2016-1-6 上午11:54:22     
					     * @param param
					     * @return     
					     */
					public String byIdDataSearch(String param);
					  
					    /**     
					     * @discription 条件查询数据
					     * @author 王帅—wes       
					     * @created 2016-1-6 上午11:54:34     
					     * @param param
					     * @return     
					     */
					public String paramDataSearch(String param);
					
}
