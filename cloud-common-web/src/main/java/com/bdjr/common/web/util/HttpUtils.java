package com.bdjr.common.web.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

public class HttpUtils {
	private static final Logger logger = LoggerFactory.getLogger(HttpUtils.class);
//	public static String GetDirectClientAddress() {
//		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
//		return request.getRemoteAddr();
//	}

	public static String getIPAddress() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		return getIPAddressByRequest(request);
	}

	public static String getIPAddressByRequest(HttpServletRequest request) {
		// wget -O aa --header="X-Forwarded-For:192.168.0.2" "http://duoapk.net/test.jsp"
		// x-forwarded-for: 192.168.0.2, 115.238.144.30
		//X-Forwarded-For: client1, proxy1, proxy2
		//从标准格式可以看出，X-Forwarded-For头信息可以有多个，中间用逗号分隔，
		//第一项为真实的客户端ip，剩下的就是曾经经过的代理或负载均衡的ip地址，
		//经过几个就会出现几个。
		//
		// 按照上图的Web架构图，可以很容易的看出，当用户请求经过CDN后到达Nginx负载均衡服务器时，
		// 其X-Forwarded-For头信息应该为 客户端IP,CDN的IP 但实际情况并非如此，
		//一般情况下CDN服务商为了自身安全考虑会将这个信息做些改动，只保留客户端IP。
		//我们可以通过php程序获得X-Forwarded-For信息或者通过Nginx的add header
		//方法来设置返回头来查看。

		//代理服务器规范里面，规定真正的来源保存在头信息  "x-forwarded-for"
		// 在转发请求的HTTP头信息中，增加了X－FORWARDED－FOR信息。
		// 用以跟踪原有的客户端IP地址和原来客户端请求的服务器地址
		String ipAddress = request.getHeader("x-forwarded-for");

		//10.105.62.88
		if (ipAddress == null ||
				//说明代理的是一个内网的地址，例如移动的网络
				ipAddress.startsWith("10." ) ||
				ipAddress.startsWith("192.168" ) ) {
			ipAddress = request.getRemoteAddr();
		}
		//严格来说，应该去取第1个值，多次转发后会有多个地址，以，分隔；
		//  ip=22.53.71.12, 211.138.237.164
		if (ipAddress!=null && ipAddress.length() > 15){
			ipAddress = ipAddress.trim();
			int pos = ipAddress.indexOf(",");
			if (pos!=-1){
				return ipAddress.substring(0,pos);
			}
		}
		if (ipAddress!=null){
			return ipAddress.trim();
		}else {
			return "";
		}

	}

	/**
	 * 向指定URL发送GET方法的请求
	 *
	 * @param url
	 *            发送请求的URL
	 * @param param
	 *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	 * @return URL 所代表远程资源的响应结果
	 */
	public static String sendGet(String url, String param) {
		String result = "";
		BufferedReader in = null;
		try {
			String urlNameString = url + "?" + param;
			URL realUrl = new URL(urlNameString);
			// 打开和URL之间的连接
			URLConnection connection = realUrl.openConnection();
			// 设置通用的请求属性
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			connection.setRequestProperty("Content-Type", "text/xml;charset=utf-8");
			// 建立实际的连接
			connection.connect();
			// 获取所有响应头字段
			Map<String, List<String>> map = connection.getHeaderFields();
			// 遍历所有的响应头字段
			for (String key : map.keySet()) {
				System.out.println(key + "--->" + map.get(key));
			}
			// 定义 BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(
					connection.getInputStream(),"utf-8"));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("发送GET请求出现异常！" + e);
			logger.error("error",e);
		}
		// 使用finally块来关闭输入流
		finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {
				logger.error("error",e2);
			}
		}
		return result;
	}
	/**
	 * 向指定 URL 发送POST方法的请求
	 *
	 * @param url
	 *            发送请求的 URL
	 * @param param
	 *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	 * @return 所代表远程资源的响应结果
	 */
	public static String sendPost(String url, String param) {
		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		try {
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			URLConnection conn = realUrl.openConnection();
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// 获取URLConnection对象对应的输出流
			out = new PrintWriter(conn.getOutputStream());
			// 发送请求参数
			out.print(param);
			// flush输出流的缓冲
			out.flush();
			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(
					new InputStreamReader(conn.getInputStream(),"utf-8"));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("发送 POST 请求出现异常！"+e);
			logger.error("error",e);
		}
		//使用finally块来关闭输出流、输入流
		finally{
			try{
				if(out!=null){
					out.close();
				}
				if(in!=null){
					in.close();
				}
			}
			catch(IOException ex){
				logger.error("error",ex);
			}
		}
		return result;
	}
}