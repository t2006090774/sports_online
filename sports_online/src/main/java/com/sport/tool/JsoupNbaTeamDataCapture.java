package com.sport.tool;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.sport.common.constant.Constant;
import com.sport.entity.base.TitleAndContent;

/**
 * @author a_kai
 */

public class JsoupNbaTeamDataCapture {
	
	public static List<TitleAndContent> dataCapture(String url,Map<String, String> logoMap) throws IOException {
		Document document = createDocument1(url);
		//创建存放数据的对象
		List<TitleAndContent> entityList = new ArrayList<TitleAndContent>();
		//声明表格存放数据实体
		TitleAndContent entity = null;
		//声明title保存list
		List<String> titleList =null;
		//声明保存内容的list
		List<TreeMap<String, String>> contentList = null;
		TreeMap<String,String> contentRowsMap = null;
		//获取table
		Elements eleh = document.select(".colLM1").select(".dataTB2");
		for(int i = 0; i<eleh.size(); i++) {
			//获取每个表格中的每一行
			Elements eleRows = eleh.get(i).select("tbody").select("tr");
			//初始化保存title对象
			titleList = new ArrayList<String>();
			//循环每一行的每一个元素
			contentList = new ArrayList<TreeMap<String,String>>();
			//初始化实体
			entity = new TitleAndContent();
			for(int j = 0;j<eleRows.size();j++) {
				if(j == 0) {
					for(Element e:eleRows.get(j).select("th:lt(5)")) {
						//保存title
						titleList.add(e.text());
					}
				}else {
					//保存内容
					contentRowsMap = new TreeMap<String,String>();
					//预留查询对应字段名
					int num = 0;
					for(Element e:eleRows.get(j).select("td:lt(5)")) {
						contentRowsMap.put(num+"", e.text());
						num++;
					}
					//插入保存的表格num(第几个表格)
					contentRowsMap.put((eleRows.size()-1)+"", i+"");
					//通过name查询图片并插入图片URL
					contentRowsMap.put(eleRows.size()+"", JsoupNbaTeamDataCapture.getLogoUrl(contentRowsMap.get("0"),logoMap));
					//保存所有行
					contentList.add(contentRowsMap);
				}
			}
			//保存title信息
			entity.setTitleList(titleList);
			//保存内容信息
			entity.setContentList(contentList);
			entityList.add(entity);
		}
		
		return entityList;
		
    }
	
	
	/**
	 * 遍历获取logoUrl地址
	 */
	public static String getLogoUrl(String name,Map<String, String> logoMap) {
		//暂无图片
		for(Map.Entry<String, String> entry :logoMap.entrySet()) {
			if(name!=null&&name.equals(entry.getKey())) {
				return entry.getValue();
			}
		}
		return Constant.NULL_LOGO;
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