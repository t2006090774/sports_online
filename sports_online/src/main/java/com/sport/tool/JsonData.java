package com.sport.tool;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author a_kai
 */

public class JsonData {

	static String[] sArr = {"http://skyapp.mackentan.com/app/v6/playlist.do?pid=150&t=1530525308000&did=UY32l3D8Qhb605993b6f&ver=172&it=425144&fit=425144&sign=ffeede9c72b5400b857a27fe2cbf8581d72aad04&appid=com.jediapp.football&idfa=EB6FA7AE-A626-44D9-9248-C841FD52029E",
					"http://skyapp.mackentan.com/app/v6/playlist.do?pid=151&t=1530525273000&did=UY32l3D8Qhb605993b6f&ver=172&it=425144&fit=425144&sign=21ede0fd0217872430bdeeb441993615d05a9885&appid=com.jediapp.football&idfa=EB6FA7AE-A626-44D9-9248-C841FD52029E",
					"http://skyapp.mackentan.com/app/v6/playlist.do?pid=561&t=1530525257000&did=UY32l3D8Qhb605993b6f&ver=172&it=425144&fit=425144&sign=44b381202374773cfa91f6773021e7d61bfc32bc&appid=com.jediapp.football&idfa=EB6FA7AE-A626-44D9-9248-C841FD52029E",
					"http://skyapp.mackentan.com/app/v6/playlist.do?pid=155&t=1530525227000&did=UY32l3D8Qhb605993b6f&ver=172&it=425144&fit=425144&sign=b6b423f2cc6ea59bc5c9470c0ee225b2fa812a9f&appid=com.jediapp.football&idfa=EB6FA7AE-A626-44D9-9248-C841FD52029E",
					"http://skyapp.mackentan.com/app/v6/playlist.do?pid=152&t=1530525190000&did=UY32l3D8Qhb605993b6f&ver=172&it=425144&fit=425144&sign=852be0eaf5700b5bd3b722b8947ad5f019027b4a&appid=com.jediapp.football&idfa=EB6FA7AE-A626-44D9-9248-C841FD52029E",
					"http://skyapp.mackentan.com/app/v6/playlist.do?pid=153&t=1530525169000&did=UY32l3D8Qhb605993b6f&ver=172&it=425144&fit=425144&sign=764a4e8bbeebd37a2cb85c685abf7f4cddb644cc&appid=com.jediapp.football&idfa=EB6FA7AE-A626-44D9-9248-C841FD52029E",
					"http://skyapp.mackentan.com/app/v6/playlist.do?pid=154&t=1530525109000&did=UY32l3D8Qhb605993b6f&ver=172&it=425144&fit=425144&sign=8e518615e93e590e81c775276748f5142b4f5f85&appid=com.jediapp.football&idfa=EB6FA7AE-A626-44D9-9248-C841FD52029E",
					"http://skyapp.mackentan.com/app/v6/playlist.do?pid=149&t=1530524751000&did=UY32l3D8Qhb605993b6f&ver=172&it=425144&fit=425144&sign=52c0e01d650e8a58032f246950d5113f5987c03e&appid=com.jediapp.football&idfa=EB6FA7AE-A626-44D9-9248-C841FD52029E"};

	public static void getEntity(){
		for(int i=0;i<sArr.length;i++){
			String s = JsonData.sendGet(sArr[i]);
			//解析json
			JSONObject data = JSONObject.fromObject(s);
			JSONObject jsonObject = (JSONObject) data.get("data");
			JSONArray jsonArray = (JSONArray) jsonObject.get("datalist");
			for(int j=0;j<jsonArray.size();j++){
				JSONObject jsonObjectTemp = (JSONObject) jsonArray.get(j);
				System.out.println(jsonObjectTemp.getString("pid")+"\t"+jsonObjectTemp.getString("url")+"\t"+jsonObjectTemp.getString("title")+"\t"+jsonObjectTemp.getString("cover"));
			}
		}
	}


	public static String sendGet(String url) {
		String result = "";
		BufferedReader in = null;
		try {
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			URLConnection connection = realUrl.openConnection();
			// 设置通用的请求属性
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 建立实际的连接
			connection.connect();
			// 获取所有响应头字段
			Map map = connection.getHeaderFields();
			// 遍历所有的响应头字段
			for (Object key : map.keySet()) {
				System.out.println(key + "--->" + map.get(key));
			}
			// 定义 BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(
					connection.getInputStream(),"UTF-8"));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("发送GET请求出现异常！" + e);
			e.printStackTrace();
		}
		// 使用finally块来关闭输入流
		finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}


}