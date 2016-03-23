package com.amap.service.impl;

/**
 * 
 *   ��������ͼ�ӿ�
 * @author wes
 *
 */
public interface AmapCloudService {
					  
					    /**     
					     * @discription ������
					     * @author ��˧��wes       
					     * @created 2016-1-6 ����11:52:35     
					     * @param param
					     * @return     
					     */
					public String createTable(String param);
					  
					    /**     
					     * @discription �ڱ��д�������
					     * @author ��˧��wes       
					     * @created 2016-1-6 ����11:52:49     
					     * @param param
					     * @return     
					     */
					public String createTableData(String param);
					  
					    /**     
					     * @discription ������������
					     * @author ��˧��wes       
					     * @created 2016-1-6 ����11:53:10     
					     * @param param
					     * @return     
					     */
					public String batchCreateData(String param);
					  
					    /**     
					     * @discription ��������
					     * @author ��˧��wes       
					     * @created 2016-1-6 ����11:53:25     
					     * @param param
					     * @return     
					     */
					public String updateData(String param);
					  
					    /**     
					     * @discription ɾ������
					     * @author ��˧��wes       
					     * @created 2016-1-6 ����11:53:38     
					     * @param param
					     * @return     
					     */
					public String deleteData(String param);
					
					
					  
					    /**     
					     * @discription ����ȫ������
					     * @author ��˧��wes       
					     * @created 2016-1-6 ����3:03:04     
					     * @param param
					     * @return     
					     */
					public String queryAllData(String param);
					  
					    /**     
					     * @discription ���ز�ѯ����
					     * @author ��˧��wes       
					     * @created 2016-1-6 ����11:53:46     
					     * @param param
					     * @return     
					     */
					public String localDataSearch(String param);
					  
					    /**     
					     * @discription �ܱ���������
					     * @author ��˧��wes       
					     * @created 2016-1-6 ����11:53:59     
					     * @param param
					     * @return     
					     */
					public String aroundDataSearch(String param);
					  
					    /**     
					     * @discription ͼ�μ�������
					     * @author ��˧��wes       
					     * @created 2016-1-6 ����11:54:11     
					     * @param param
					     * @return     
					     */
					public String polygonDataSearch(String param);
					  
					    /**     
					     * @discription id��������
					     * @author ��˧��wes       
					     * @created 2016-1-6 ����11:54:22     
					     * @param param
					     * @return     
					     */
					public String byIdDataSearch(String param);
					  
					    /**     
					     * @discription ������ѯ����
					     * @author ��˧��wes       
					     * @created 2016-1-6 ����11:54:34     
					     * @param param
					     * @return     
					     */
					public String paramDataSearch(String param);
					
}
