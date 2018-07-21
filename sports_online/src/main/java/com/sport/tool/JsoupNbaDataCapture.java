package com.sport.tool;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author a_kai
 */

public class JsoupNbaDataCapture {
	
	@Transactional
	public static List<ArrayList<String>> dataCapture(String url) throws IOException {
		Document document = createDocument1(url);
		Elements eles = document.select(".dataTb01").select("tbody").select("tr");//:lt(4).select("li")
		//创建list存放tr
		List<ArrayList<String>> lists = new ArrayList<ArrayList<String>>();
		ArrayList<String> list = null;
		int i = 0;
		for(Element ele:eles) {
			list = new ArrayList<String>();
			if(i==0) {
				list.add(ele.select("td:eq(1)").text());
			}
			for(Element e:ele.select("td:gt(1)")) {
				list.add(e.text());
			}
			lists.add(list);
		}
		return lists;
    }
	
	
	/**
	 * 将地址解析为Document对象
	 * @param url
	 * @return	Document
	 * @throws IOException
	 */
	public static Document createDocument1(String url) throws IOException {
		
		//未执行异步加载数据
        Document document=Jsoup.connect(url)
                //模拟火狐浏览器
                .userAgent("Mozilla/4.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0)")
                .get();
		return document;
	}
	
}