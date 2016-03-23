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
 * Title: AmapCloudServiceImpl.java Description: 云图服务实现
 * 
 * @author 王帅—wes
 * @created 2016-1-6 上午11:55:10
 */

public class AmapCloudServiceImpl implements AmapCloudService {

	// httpclient,用于发起http请求
	private static HttpClient client = HttpClients.createDefault();
	// jackson的ObjectMapper,用于在json字符串和Java对象间转换
//	public static ObjectMapper objectMapper = new ObjectMapper();

	public static final String BOUNDARY = "--------------bounddry---------------";

	/**
	 * @discription 创建表
	 * @author 王帅—wes
	 * @created 2016-1-6 上午11:52:35
	 * @param param
	 * @return
	 */
	public String createTable(String param) {
		// TODO Auto-generated method stub
		try {
			Map<String, String> parammap = JsonUtil.getMapFromJson(param);
			String name = parammap.get("name");
			String key = AmapConfig.inistance().getKey();
			// 创建httppost对象，设置云存储API，创建表服务地址,http://yuntuapi.amap.com/datamanage/table/create
			HttpPost post = new HttpPost(InitUtil.instance.getCreatetableurl());
			post.addHeader("Content-Type", "application/x-www-form-urluncoded");
			// 创建参数列表
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("key", key));
			params.add(new BasicNameValuePair("name", name));
			// 创建httpentity
			UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params,
					"UTF-8");
			post.setEntity(entity);
			// 执行并解析返回结果,反序列化为Java对象
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
	 * @discription 在表中创建数据
	 * @author 王帅—wes
	 * @created 2016-1-6 上午11:52:49
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
			// 创建httppots对象,设置云存储api,创建单条数据服务地址,http://yuntuapi.amap.com/datamanage/data/create
			HttpPost post = new HttpPost(InitUtil.instance.getCreatedataurl());
			post.addHeader("Content-Type", "application/x-www-form-urluncoded");
			String key = AmapConfig.inistance().getKey();
			// 创建参数列表，并添加参数
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("key", key));
			params.add(new BasicNameValuePair("tableid", tableid));
			params.add(new BasicNameValuePair("data", data));
			// 创建httpentity
			UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params,
					"UTF-8");
			post.setEntity(entity);
			// 执行请求
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
	 * @discription 批量创建数据
	 * @author 王帅—wes
	 * @created 2016-1-6 上午11:53:10
	 * @param param
	 * @return
	 */
	public String batchCreateData(String param) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @discription 更新数据
	 * @author 王帅—wes
	 * @created 2016-1-6 上午11:53:25
	 * @param param
	 * @return
	 */
	public String updateData(String param) {
		// TODO Auto-generated method stub
		// 创建httppost请求，设置云存储API,更新数据服务地址,http://yuntuapi.amap.com/datamanage/data/update
		try {
			HttpPost post = new HttpPost(InitUtil.instance.getUpdatedataurl());
			post.addHeader("Content-Type", "application/x-www-form-urluncoded");
			String key = AmapConfig.inistance().getKey();
			Map<String, String> parammap = JsonUtil.getMapFromJson(param);
			parammap = JsonUtil.getMapFromJson(param);
			String tableid = parammap.get("tableid");
			String data = JSONObject.fromObject(parammap.get("data"))
					.toString();
			// 创建参数列表并设置参数
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("key", key));
			params.add(new BasicNameValuePair("tableid", tableid));
			params.add(new BasicNameValuePair("data", data));
			// 创建httpentity
			HttpEntity entity = new UrlEncodedFormEntity(params, "UTF-8");
			post.setEntity(entity);
			// 执行请求
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
	 * @discription 删除数据
	 * @author 王帅—wes
	 * @created 2016-1-6 上午11:53:38
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
			// 创建httpget请求,设置云存储API,删除数据服务地址,http://yuntuapi.amap.com/datamanage/data/delete,并拼接参数
			HttpGet get = new HttpGet(InitUtil.instance.getDeletedataurl()
					+ "?tableid=" + tableid + "&key=" + key + "&ids=" + ids);
			// 执行请求
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
	 * @discription 检索全表数据
	 * @author 王帅—wes
	 * @created 2016-1-6 下午3:03:04
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
			// 创建httpget对象，设置云检索API,遍历表数据服务地址,http://yuntuapi.amap.com/datamanage/data/list
			HttpGet get = new HttpGet(InitUtil.instance.getParamlistdataurl()
					+ "?tableid=" + tableid + "&key=" + key + "&limit=" + rows
					+ "&page=" + page);
			// 执行请求
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
	 * @discription 本地查询数据
	 * @author 王帅—wes
	 * @created 2016-1-6 上午11:53:46
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
			// 创建httpget对象，设置云检索API,遍历表数据服务地址,http://yuntuapi.amap.com/datamanage/data/list
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
			// 执行请求
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
	 * @discription 周边搜索数据
	 * @author 王帅—wes
	 * @created 2016-1-6 上午11:53:59
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
			// 创建httpget对象，设置云检索API,遍历表数据服务地址,http://yuntuapi.amap.com/datamanage/data/list
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
			// 执行请求
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
	 * @discription 图形检索数据
	 * @author 王帅—wes
	 * @created 2016-1-6 上午11:54:11
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
			// 执行请求
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
	 * @discription id搜索数据
	 * @author 王帅—wes
	 * @created 2016-1-6 上午11:54:22
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
			// 执行请求
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
		// //创建表
		// String param = "{\"name\":\"userloginmap\"}";
		// String val = amapCloudService.createTable(param);
		 //创建数据
//		 String param =
//		 "{\"tableid\":\"568cadac305a2a31f60fbab6\",\"data\":{\"_name\":\"驴\",\"_location\":\"116.34657461579138,39.975494268313135\",\"coordtype\":\"gps\",\"_address\":\"饺子\",\"uid\":\"a547708f-6d8c-4fe4-8e24-e6df917d235e\"}}";
//		 String val = amapCloudService.createTableData(param);
		// 更新数据
		// String param =
		// "{\"tableid\":\"568cadac305a2a31f60fbab6\",\"data\":{\"_id\":\"5\",\"_name\":\"修改的数据\",\"_location\":\"116.34657461579138,39.975494268313135\",\"coordtype\":\"gps\",\"_address\":\"更新的地址\",\"uid\":\"a547708f-6d8c-4fe4-8e24-e6df917d235e\"}}";
		// String val = amapCloudService.updateData(param);
		// 删除数据
		// String param =
		// "{\"tableid\":\"568cadac305a2a31f60fbab6\",\"ids\":\"3,4\"}";
		// String val = amapCloudService.deleteData(param);
		// System.out.println(val);
		// 检索全表数据
		// String param =
		// "{\"tableid\":\"568cadac305a2a31f60fbab6\",\"rows\":\"10\",\"page\":\"1\"}";
		// String val = amapCloudService.queryAllData(param);
		// System.out.println(val);
		// 本地检索数据
//		 String param =
//		 "{\"tableid\":\"568cadac305a2a31f60fbab6\",\"rows\":\"10\",\"page\":\"1\",\"keywords\":\"修改\",\"city\":\"北京\"}";
//		 String val = amapCloudService.localDataSearch(param);
//		 System.out.println(val);
			// 周边检索数据
//		 String param =
//		 "{\"tableid\":\"568cadac305a2a31f60fbab6\",\"rows\":\"10\",\"page\":\"1\",\"keywords\":\"\",\"center\":\"116.346574,39.975494\",\"radius\":\"10000\"}";
//		 String val = amapCloudService.aroundDataSearch(param);
//		 System.out.println(val);
		
		// 多边形检索数据
//		 String param =
//		 "{\"tableid\":\"568cadac305a2a31f60fbab6\",\"rows\":\"10\",\"page\":\"1\",\"keywords\":\"\",\"polygon\":\"115.7409668,40.12009038;115.59127808,39.98869502;115.8631897,39.91816285;115.92224121,40.06546068;115.7409668,40.12009038\"}";
//		 String val = amapCloudService.polygonDataSearch(param);
//		 System.out.println(val);
			//id检索数据
//		 String param =
//		 "{\"tableid\":\"568cadac305a2a31f60fbab6\",\"_id\":\"7\"}";
//		 String val = amapCloudService.byIdDataSearch(param);
//		 System.out.println(val);
		
		
	}
	/**
	 * @discription 条件查询数据
	 * @author 王帅—wes
	 * @created 2016-1-6 上午11:54:34
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
			// 创建httpget对象，设置云检索API,遍历表数据服务地址,http://yuntuapi.amap.com/datamanage/data/list
			HttpGet get = new HttpGet(InitUtil.instance.getParamlistdataurl()
					+ "?tableid=" + tableid + "&key=" + key + "&limit=" + rows
					+ "&page=" + page);
			// 执行请求
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
