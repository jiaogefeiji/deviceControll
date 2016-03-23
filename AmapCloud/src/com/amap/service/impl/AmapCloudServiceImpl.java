package com.amap.service.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.amap.configData.AmapConfig;
import com.amap.utils.InitUtil;
import com.amap.utils.JsonUtil;

/**
 * Title: AmapCloudServiceImpl.java Description: ��ͼ����ʵ��
 * 
 * @author ��˧��wes
 * @created 2016-1-6 ����11:55:10
 */

public class AmapCloudServiceImpl implements AmapCloudService {

	// httpclient,���ڷ���http����
	private static HttpClient client = HttpClients.createDefault();
	// jackson��ObjectMapper,������json�ַ�����Java�����ת��
//	public static ObjectMapper objectMapper = new ObjectMapper();

	public static final String BOUNDARY = "--------------bounddry---------------";

	/**
	 * @discription ������
	 * @author ��˧��wes
	 * @created 2016-1-6 ����11:52:35
	 * @param param
	 * @return
	 */
	public String createTable(String param) {
		// TODO Auto-generated method stub
		try {
			Map<String, String> parammap = JsonUtil.getMapFromJson(param);
			String name = parammap.get("name");
			String key = AmapConfig.inistance().getKey();
			// ����httppost���������ƴ洢API������������ַ,http://yuntuapi.amap.com/datamanage/table/create
			HttpPost post = new HttpPost(InitUtil.instance.getCreatetableurl());
			post.addHeader("Content-Type", "application/x-www-form-urluncoded");
			// ���������б�
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("key", key));
			params.add(new BasicNameValuePair("name", name));
			// ����httpentity
			UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params,
					"UTF-8");
			post.setEntity(entity);
			// ִ�в��������ؽ��,�����л�ΪJava����
			HttpResponse resp = client.execute(post);
			HttpEntity resultentity = resp.getEntity();
			String resultval = EntityUtils.toString(resultentity, "UTF-8");
			System.out.println(resultval);
			Map<String, Object> resultmap = JsonUtil.getMapFromJson(resultval);
			String status = resultmap.get("status").toString();
			if (status != null && status.equals("1")) {
				return resultmap.get("tableid").toString();
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @discription �ڱ��д�������
	 * @author ��˧��wes
	 * @created 2016-1-6 ����11:52:49
	 * @param param
	 * @return
	 */
	public String createTableData(String param) {
		// TODO Auto-generated method stub
		try {
			Map<String, String> parammap = JsonUtil.getMapFromJson(param);
			parammap = JsonUtil.getMapFromJson(param);
			String tableid = parammap.get("tableid");
			String data = JSONObject.fromObject(parammap.get("data"))
					.toString();
			// ����httppots����,�����ƴ洢api,�����������ݷ����ַ,http://yuntuapi.amap.com/datamanage/data/create
			HttpPost post = new HttpPost(InitUtil.instance.getCreatedataurl());
			post.addHeader("Content-Type", "application/x-www-form-urluncoded");
			String key = AmapConfig.inistance().getKey();
			// ���������б�����Ӳ���
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("key", key));
			params.add(new BasicNameValuePair("tableid", tableid));
			params.add(new BasicNameValuePair("data", data));
			// ����httpentity
			UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params,
					"UTF-8");
			post.setEntity(entity);
			// ִ������
			HttpResponse resp = client.execute(post);
			HttpEntity resultentity = resp.getEntity();
			String resultval = EntityUtils.toString(resultentity, "UTF-8");
			System.out.println(resultval);
			Map<String, Object> resultmap = JsonUtil.getMapFromJson(resultval);
			String status = resultmap.get("status").toString();
			if (status != null && status.equals("1")) {
				return resultmap.get("_id").toString();
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @discription ������������
	 * @author ��˧��wes
	 * @created 2016-1-6 ����11:53:10
	 * @param param
	 * @return
	 */
	public String batchCreateData(String param) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @discription ��������
	 * @author ��˧��wes
	 * @created 2016-1-6 ����11:53:25
	 * @param param
	 * @return
	 */
	public String updateData(String param) {
		// TODO Auto-generated method stub
		// ����httppost���������ƴ洢API,�������ݷ����ַ,http://yuntuapi.amap.com/datamanage/data/update
		try {
			HttpPost post = new HttpPost(InitUtil.instance.getUpdatedataurl());
			post.addHeader("Content-Type", "application/x-www-form-urluncoded");
			String key = AmapConfig.inistance().getKey();
			Map<String, String> parammap = JsonUtil.getMapFromJson(param);
			parammap = JsonUtil.getMapFromJson(param);
			String tableid = parammap.get("tableid");
			String data = JSONObject.fromObject(parammap.get("data"))
					.toString();
			// ���������б����ò���
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("key", key));
			params.add(new BasicNameValuePair("tableid", tableid));
			params.add(new BasicNameValuePair("data", data));
			// ����httpentity
			HttpEntity entity = new UrlEncodedFormEntity(params, "UTF-8");
			post.setEntity(entity);
			// ִ������
			HttpResponse resp = client.execute(post);
			HttpEntity resultentity = resp.getEntity();
			String resultval = EntityUtils.toString(resultentity, "UTF-8");
			System.out.println(resultval);
			Map<String, Object> resultmap = JsonUtil.getMapFromJson(resultval);
			String status = resultmap.get("status").toString();
			if (status != null && status.equals("1")) {
				return status;
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @discription ɾ������
	 * @author ��˧��wes
	 * @created 2016-1-6 ����11:53:38
	 * @param param
	 * @return
	 */
	public String deleteData(String param) {
		// TODO Auto-generated method stub
		try {
			Map<String, String> parammap = JsonUtil.getMapFromJson(param);
			parammap = JsonUtil.getMapFromJson(param);
			String tableid = parammap.get("tableid");
			String ids = parammap.get("ids");
			String key = AmapConfig.inistance().getKey();
			String data = JSONObject.fromObject(parammap.get("data"))
					.toString();
			// ����httpget����,�����ƴ洢API,ɾ�����ݷ����ַ,http://yuntuapi.amap.com/datamanage/data/delete,��ƴ�Ӳ���
			HttpGet get = new HttpGet(InitUtil.instance.getDeletedataurl()
					+ "?tableid=" + tableid + "&key=" + key + "&ids=" + ids);
			// ִ������
			HttpResponse resp = client.execute(get);
			HttpEntity resultentity = resp.getEntity();
			String resultval = EntityUtils.toString(resultentity, "UTF-8");
			System.out.println(resultval);
			Map<String, Object> resultmap = JsonUtil.getMapFromJson(resultval);
			String status = resultmap.get("status").toString();
			if (status != null && status.equals("1")) {
				return status;
			}
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @discription ����ȫ������
	 * @author ��˧��wes
	 * @created 2016-1-6 ����3:03:04
	 * @param param
	 * @return
	 */
	public String queryAllData(String param) {
		// TODO Auto-generated method stub
		try {
			Map<String, String> parammap = JsonUtil.getMapFromJson(param);
			parammap = JsonUtil.getMapFromJson(param);
			String tableid = parammap.get("tableid");
			String rows = parammap.get("rows");
			String page = parammap.get("page");
			String key = AmapConfig.inistance().getKey();
			// ����httpget���������Ƽ���API,���������ݷ����ַ,http://yuntuapi.amap.com/datamanage/data/list
			HttpGet get = new HttpGet(InitUtil.instance.getParamlistdataurl()
					+ "?tableid=" + tableid + "&key=" + key + "&limit=" + rows
					+ "&page=" + page);
			// ִ������
			HttpResponse resp = client.execute(get);
			HttpEntity resultentity = resp.getEntity();
			String resultval = EntityUtils.toString(resultentity, "UTF-8");
			System.out.println(resultval);
			Map<String, Object> resultmap = JsonUtil.getMapFromJson(resultval);
			String status = resultmap.get("status").toString();
			if (status != null && status.equals("1")) {
				return resultval;
			}
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}


	/**
	 * @discription ���ز�ѯ����
	 * @author ��˧��wes
	 * @created 2016-1-6 ����11:53:46
	 * @param param
	 * @return
	 */
	public String localDataSearch(String param) {
		// TODO Auto-generated method stub
		try {
			Map<String, String> parammap = JsonUtil.getMapFromJson(param);
			parammap = JsonUtil.getMapFromJson(param);
			String tableid = parammap.get("tableid");
			String keywords = parammap.get("keywords");
			String city = parammap.get("city");
			String filter = parammap.get("filter");
			String sortrule = parammap.get("sortrule");
			String rows = parammap.get("rows");
			String page = parammap.get("page");
			String key = AmapConfig.inistance().getKey();
			// ����httpget���������Ƽ���API,���������ݷ����ַ,http://yuntuapi.amap.com/datamanage/data/list
			StringBuffer stringBuffer = new StringBuffer(
					InitUtil.instance.getLocaldatasearchurl() + "?tableid="
							+ tableid + "&key=" + key + "&limit=" + rows
							+ "&page=" + page);
			if (keywords != null && !keywords.equals("")) {
				stringBuffer.append("&keywords=" + keywords);
			}
			if (city != null && !city.equals("")) {
				stringBuffer.append("&city=" + city);
			}
			if (filter != null && !filter.equals("")) {
				stringBuffer.append("&filter=" + filter);
			}
			if (sortrule != null && !sortrule.equals("")) {
				stringBuffer.append("&sortrule=" + keywords);
			}
			HttpGet get = new HttpGet(stringBuffer.toString());
			// ִ������
			HttpResponse resp = client.execute(get);
			HttpEntity resultentity = resp.getEntity();
			String resultval = EntityUtils.toString(resultentity, "UTF-8");
			System.out.println(resultval);
			Map<String, Object> resultmap = JsonUtil.getMapFromJson(resultval);
			String status = resultmap.get("status").toString();
			if (status != null && status.equals("1")) {
				return resultval;
			}
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @discription �ܱ���������
	 * @author ��˧��wes
	 * @created 2016-1-6 ����11:53:59
	 * @param param
	 * @return
	 */
	public String aroundDataSearch(String param) {
		// TODO Auto-generated method stub
		try {
			Map<String, String> parammap = JsonUtil.getMapFromJson(param);
			parammap = JsonUtil.getMapFromJson(param);
			String tableid = parammap.get("tableid");
			String keywords = parammap.get("keywords");
			String center = parammap.get("center");
			String filter = parammap.get("filter");
			String radius = parammap.get("radius");
			String rows = parammap.get("rows");
			String page = parammap.get("page");
			String key = AmapConfig.inistance().getKey();
			// ����httpget���������Ƽ���API,���������ݷ����ַ,http://yuntuapi.amap.com/datamanage/data/list
			StringBuffer stringBuffer = new StringBuffer(
					InitUtil.instance.getArounddatasearchurl() + "?tableid="
							+ tableid + "&key=" + key + "&limit=" + rows
							+ "&page=" + page);
			if (keywords != null && !keywords.equals("")) {
				stringBuffer.append("&keywords=" + keywords);
			}
			if (center != null && !center.equals("")) {
				stringBuffer.append("&center=" + center);
			}
			if (filter != null && !filter.equals("")) {
				stringBuffer.append("&filter=" + filter);
			}
			if (radius != null && !radius.equals("")) {
				stringBuffer.append("&radius=" + radius);
			}
			HttpGet get = new HttpGet(stringBuffer.toString());
			// ִ������
			HttpResponse resp = client.execute(get);
			HttpEntity resultentity = resp.getEntity();
			String resultval = EntityUtils.toString(resultentity, "UTF-8");
			System.out.println(resultval);
			Map<String, Object> resultmap = JsonUtil.getMapFromJson(resultval);
			String status = resultmap.get("status").toString();
			if (status != null && status.equals("1")) {
				return resultval;
			}
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * @discription ͼ�μ�������
	 * @author ��˧��wes
	 * @created 2016-1-6 ����11:54:11
	 * @param param
	 * @return
	 */
	public String polygonDataSearch(String param) {
		// TODO Auto-generated method stub
		try {
			Map<String, String> parammap = JsonUtil.getMapFromJson(param);
			parammap = JsonUtil.getMapFromJson(param);
			String tableid = parammap.get("tableid");
			String keywords = parammap.get("keywords");
			String polygon = parammap.get("polygon");
			String filter = parammap.get("filter");
			String rows = parammap.get("rows");
			String page = parammap.get("page");
			String key = AmapConfig.inistance().getKey();
			StringBuffer stringBuffer = new StringBuffer(
					InitUtil.instance.getPolygondatasearchurl() + "?tableid="
							+ tableid + "&key=" + key + "&limit=" + rows
							+ "&page=" + page);
			if (keywords != null && !keywords.equals("")) {
				stringBuffer.append("&keywords=" + keywords);
			}
			if (polygon != null && !polygon.equals("")) {
				stringBuffer.append("&polygon=" + polygon);
			}
			if (filter != null && !filter.equals("")) {
				stringBuffer.append("&filter=" + filter);
			}
			HttpGet get = new HttpGet(stringBuffer.toString());
			// ִ������
			HttpResponse resp = client.execute(get);
			HttpEntity resultentity = resp.getEntity();
			String resultval = EntityUtils.toString(resultentity, "UTF-8");
			System.out.println(resultval);
			Map<String, Object> resultmap = JsonUtil.getMapFromJson(resultval);
			String status = resultmap.get("status").toString();
			if (status != null && status.equals("1")) {
				return resultval;
			}
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @discription id��������
	 * @author ��˧��wes
	 * @created 2016-1-6 ����11:54:22
	 * @param param
	 * @return
	 */
	public String byIdDataSearch(String param) {
		// TODO Auto-generated method stub
		try {
			Map<String, String> parammap = JsonUtil.getMapFromJson(param);
			parammap = JsonUtil.getMapFromJson(param);
			String tableid = parammap.get("tableid");
			String id = parammap.get("_id");
			String key = AmapConfig.inistance().getKey();
			StringBuffer stringBuffer = new StringBuffer(
					InitUtil.instance.getIddatasearchurl() + "?tableid="
							+ tableid + "&key=" + key);
			if (id != null && !id.equals("")) {
				stringBuffer.append("&_id=" + id);
			}
			HttpGet get = new HttpGet(stringBuffer.toString());
			// ִ������
			HttpResponse resp = client.execute(get);
			HttpEntity resultentity = resp.getEntity();
			String resultval = EntityUtils.toString(resultentity, "UTF-8");
			System.out.println(resultval);
			Map<String, Object> resultmap = JsonUtil.getMapFromJson(resultval);
			String status = resultmap.get("status").toString();
			if (status != null && status.equals("1")) {
				return resultval;
			}
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public static void main(String[] args) {
		AmapCloudService amapCloudService = new AmapCloudServiceImpl();
		// //������
		// String param = "{\"name\":\"userloginmap\"}";
		// String val = amapCloudService.createTable(param);
		 //��������
//		 String param =
//		 "{\"tableid\":\"568cadac305a2a31f60fbab6\",\"data\":{\"_name\":\"¿\",\"_location\":\"116.34657461579138,39.975494268313135\",\"coordtype\":\"gps\",\"_address\":\"����\",\"uid\":\"a547708f-6d8c-4fe4-8e24-e6df917d235e\"}}";
//		 String val = amapCloudService.createTableData(param);
		// ��������
		// String param =
		// "{\"tableid\":\"568cadac305a2a31f60fbab6\",\"data\":{\"_id\":\"5\",\"_name\":\"�޸ĵ�����\",\"_location\":\"116.34657461579138,39.975494268313135\",\"coordtype\":\"gps\",\"_address\":\"���µĵ�ַ\",\"uid\":\"a547708f-6d8c-4fe4-8e24-e6df917d235e\"}}";
		// String val = amapCloudService.updateData(param);
		// ɾ������
		// String param =
		// "{\"tableid\":\"568cadac305a2a31f60fbab6\",\"ids\":\"3,4\"}";
		// String val = amapCloudService.deleteData(param);
		// System.out.println(val);
		// ����ȫ������
		// String param =
		// "{\"tableid\":\"568cadac305a2a31f60fbab6\",\"rows\":\"10\",\"page\":\"1\"}";
		// String val = amapCloudService.queryAllData(param);
		// System.out.println(val);
		// ���ؼ�������
//		 String param =
//		 "{\"tableid\":\"568cadac305a2a31f60fbab6\",\"rows\":\"10\",\"page\":\"1\",\"keywords\":\"�޸�\",\"city\":\"����\"}";
//		 String val = amapCloudService.localDataSearch(param);
//		 System.out.println(val);
			// �ܱ߼�������
//		 String param =
//		 "{\"tableid\":\"568cadac305a2a31f60fbab6\",\"rows\":\"10\",\"page\":\"1\",\"keywords\":\"\",\"center\":\"116.346574,39.975494\",\"radius\":\"10000\"}";
//		 String val = amapCloudService.aroundDataSearch(param);
//		 System.out.println(val);
		
		// ����μ�������
//		 String param =
//		 "{\"tableid\":\"568cadac305a2a31f60fbab6\",\"rows\":\"10\",\"page\":\"1\",\"keywords\":\"\",\"polygon\":\"115.7409668,40.12009038;115.59127808,39.98869502;115.8631897,39.91816285;115.92224121,40.06546068;115.7409668,40.12009038\"}";
//		 String val = amapCloudService.polygonDataSearch(param);
//		 System.out.println(val);
			//id��������
//		 String param =
//		 "{\"tableid\":\"568cadac305a2a31f60fbab6\",\"_id\":\"7\"}";
//		 String val = amapCloudService.byIdDataSearch(param);
//		 System.out.println(val);
		
		
	}
	/**
	 * @discription ������ѯ����
	 * @author ��˧��wes
	 * @created 2016-1-6 ����11:54:34
	 * @param param
	 * @return
	 */
	public String paramDataSearch(String param) {
		// TODO Auto-generated method stub
		try {
			Map<String, String> parammap = JsonUtil.getMapFromJson(param);
			parammap = JsonUtil.getMapFromJson(param);
			String tableid = parammap.get("tableid");
			String rows = parammap.get("rows");
			String page = parammap.get("page");
			String key = AmapConfig.inistance().getKey();
			// ����httpget���������Ƽ���API,���������ݷ����ַ,http://yuntuapi.amap.com/datamanage/data/list
			HttpGet get = new HttpGet(InitUtil.instance.getParamlistdataurl()
					+ "?tableid=" + tableid + "&key=" + key + "&limit=" + rows
					+ "&page=" + page);
			// ִ������
			HttpResponse resp = client.execute(get);
			HttpEntity resultentity = resp.getEntity();
			String resultval = EntityUtils.toString(resultentity, "UTF-8");
			System.out.println(resultval);
			Map<String, Object> resultmap = JsonUtil.getMapFromJson(resultval);
			String status = resultmap.get("status").toString();
			if (status != null && status.equals("1")) {
				return resultval;
			}
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
