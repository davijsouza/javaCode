package com.souche.salt_common_01.utils;

import java.io.IOException;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;

public class HttpClientUtil {

	public static String doGet(String url, Map<String, String> param) {

		return doGet( url, param,null);
	}
	public static String doGet(String url, Map<String, String> param,Map<String, String> headers) {

		// 创建Httpclient对象
		CloseableHttpClient httpclient = HttpClients.createDefault();

		String resultString = "";
		CloseableHttpResponse response = null;
		try {
			// 创建uri
			URIBuilder builder = new URIBuilder(url);
			if (param != null) {
				for (String key : param.keySet()) {
					//拼接请求参数到url后
					builder.addParameter(key, param.get(key));
				}
			}
			URI uri = builder.build();

			// 创建http GET请求
			HttpGet httpGet = new HttpGet(uri);
			if(headers!=null){
				for (String key : headers.keySet()) {
					//拼接请求参数到url后

					httpGet.setHeader(key, headers.get(key));
				}
			}

			// 执行请求
			response = httpclient.execute(httpGet);
			// 判断返回状态是否为200
			if (response.getStatusLine().getStatusCode() == 200) {
				//EntityUtils.toString:
				resultString = EntityUtils.toString(response.getEntity(), "UTF-8");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (response != null) {
					response.close();
				}
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return resultString;
	}

	public static String doGet(String url) {
		return doGet(url, null);
	}

	public static String doPost(String url, Map<String, String> param) {

		return doPost(url,param,null);
	}
	public static String doPost(String url, Map<String, String> param,Map<String, String> headers) {
		// 创建Httpclient对象
		CloseableHttpClient httpClient = HttpClients.createDefault();
		CloseableHttpResponse response = null;
		String resultString = "";
		try {
			// 创建Http Post请求
			HttpPost httpPost = new HttpPost(url);
			if(headers!=null){
				for (String key : headers.keySet()) {
					//拼接请求参数到url后

					httpPost.setHeader(key, headers.get(key));
				}
			}

			//httpPost.setHeader("Content-Type","application/x-www-form-urlencoded");
			// 创建参数列表
			if (param != null) {
				List<NameValuePair> paramList = new ArrayList<>();
				for (String key : param.keySet()) {
					paramList.add(new BasicNameValuePair(key, param.get(key)));
				}
				// 模拟表单: 构造一个form表单式的实体
				UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList);
				// 将请求实体设置到httpPost对象中
				httpPost.setEntity(entity);
			}
			// 执行http请求
			response = httpClient.execute(httpPost);
			resultString = EntityUtils.toString(response.getEntity(), "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				response.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return resultString;
	}

	public static String doPost(String url) {
		return doPost(url, null);
	}

	public static String doPostJson(String url, String json) {

		return doPostJson(url,json,null);
	}

	public static String doPostJson(String url, String json,Map<String,String> header) {
		// 创建Httpclient对象
		CloseableHttpClient httpClient = HttpClients.createDefault();
		CloseableHttpResponse response = null;
		String resultString = "";
		try {
			// 创建Http Post请求

			HttpPost httpPost = new HttpPost(url);
			if(header!=null){
				for (String key : header.keySet()) {
					//拼接请求参数到url后
					httpPost.setHeader(key, header.get(key));
				}
			}
			//httpPost.setHeader("Content-type","application/json");
			// 创建请求内容
			StringEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);
			httpPost.setEntity(entity);
			// 执行http请求
			response = httpClient.execute(httpPost);
			resultString = EntityUtils.toString(response.getEntity(), "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				response.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return resultString;
	}

///-------
public static String doDelete(String url,Map<String, String> headers) {
	// 创建Httpclient对象
	CloseableHttpClient httpClient = HttpClients.createDefault();
	CloseableHttpResponse response = null;
	String resultString = "";
	try {
		// 创建Http Post请求
		HttpDelete httpDelete = new HttpDelete(url);
		if(headers!=null){
			for (String key : headers.keySet()) {
				//拼接请求参数到url后

				httpDelete.setHeader(key, headers.get(key));
			}
		}

		//httpPost.setHeader("Content-Type","application/x-www-form-urlencoded");
		// 创建参数列表
		/*if (param != null) {
			List<NameValuePair> paramList = new ArrayList<>();
			for (String key : param.keySet()) {
				paramList.add(new BasicNameValuePair(key, param.get(key)));
			}
			// 模拟表单: 构造一个form表单式的实体
			UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList);
			// 将请求实体设置到httpPost对象中

		}*/
		// 执行http请求
		response = httpClient.execute(httpDelete);
		resultString = EntityUtils.toString(response.getEntity(), "utf-8");
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		try {
			response.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	return resultString;
}



}
