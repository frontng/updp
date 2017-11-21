package com.upanda.updp.common.util;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;

/**
 * Http客户端请求工具类
 * @date 2017年9月5日
 * @author Front Ng
 *
 */
public class HttpClientUtils {
	
	private static final Logger logger = LoggerFactory.getLogger(HttpClientUtils.class);

	private static PoolingHttpClientConnectionManager cm = null;

	private static CloseableHttpClient httpClient = null;

	static {
		init();
	}

	public static void init() {
		ConnectionSocketFactory plainsf = PlainConnectionSocketFactory.getSocketFactory();
		LayeredConnectionSocketFactory sslsf = SSLConnectionSocketFactory.getSocketFactory();

		Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
				.register("https", sslsf).register("http", plainsf).build();
		cm = new PoolingHttpClientConnectionManager(registry);
		cm.setMaxTotal(200);// 最大连接数
		cm.setDefaultMaxPerRoute(20);// 每个路由基础的连接
		httpClient = HttpClients.custom().setConnectionManager(cm).build();
	}

	public static String getToUrl(String url) {
		// 参数检查
		if (httpClient == null) {
			throw new RuntimeException("httpclient not init.");
		}
		if (StringUtils.isEmpty(url)) {
			throw new RuntimeException("url is blank.");
		}

		HttpGet httpGet = new HttpGet(url);
		CloseableHttpResponse response = null;
		try {

			// 执行请求
			response = httpClient.execute(httpGet, HttpClientContext.create());

			// 转换结果
			HttpEntity entity = response.getEntity();
			String html = EntityUtils.toString(entity);

			// 消费掉内容
			EntityUtils.consume(entity);
			return html;
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			if (response != null) {
				try {
					response.close();
				} catch (IOException e) {
					logger.error(e.getMessage());
				}
			}
			httpGet.releaseConnection();
		}
	}

	/**
	 * Post方式取得URL的内容，默认为"application/x-www-form-urlencoded"格式，charset为UTF-8.
	 * 
	 * @param url
	 *            访问的网址
	 * @param content
	 *            提交的数据
	 * @return
	 */
	public static String postToUrl(String url, String content) {
		return postToUrl(url, content, "application/x-www-form-urlencoded", "UTF-8");
	}

	/**
	 * Post方式取得URL的内容.
	 * 
	 * @param url
	 *            访问的网址
	 * @param content
	 *            提交的数据
	 * @return
	 */
	public static String postToUrl(String url, String content, String contentType, String charset) {
		
		// 参数检查
		if (httpClient == null) {
			throw new RuntimeException("httpclient not init.");
		}
		if (StringUtils.isEmpty(url)) {
			throw new RuntimeException("url is blank.");
		}

		HttpPost httpPost = new HttpPost(url);

		
		// 设置内容
		ContentType type = ContentType.create(contentType, Charset.forName(charset));
		StringEntity reqEntity = new StringEntity(content, type);
		httpPost.setEntity(reqEntity);
		httpPost.addHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE .0; Windows NT 6.1; Trident/4.0; SLCC2;)");
		httpPost.addHeader("Accept-Encoding", "*");

		// 设置请求和传输超时时间
		RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(30000).setConnectTimeout(30000).build();
		httpPost.setConfig(requestConfig);

		CloseableHttpResponse response = null;
		try {
			// 执行请求
			response = httpClient.execute(httpPost, HttpClientContext.create());

			// 转换结果
			HttpEntity entity = response.getEntity();
			String html = EntityUtils.toString(entity, charset);

			// 消费掉内容
			EntityUtils.consume(entity);
			return html;
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			if (response != null) {
				try {
					response.close();
				} catch (IOException e) {
					logger.error(e.getMessage());
				}
			}
			httpPost.releaseConnection();
		}
	}
	
	
	@SuppressWarnings("unchecked")
	public static Map<String,String> getForJson(String url) {
		String result = getToUrl(url);
		return JSON.parseObject(result, Map.class);
	}
	
	@SuppressWarnings("unchecked")
	public static Map<String,String> postForJson(String url, Map<String,String> params) {
		String result = postToUrl(url, JSON.toJSONString(params), "application/json", "UTF-8");
		return JSON.parseObject(result, Map.class);
	}
}
