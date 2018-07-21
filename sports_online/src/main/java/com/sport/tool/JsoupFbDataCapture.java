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

import com.sport.entity.base.TitleAndContent;

/**
 * @author a_kai
 */

public class JsoupFbDataCapture {
	
	/**
	 * 爬取所有页面球员信息
	 */
	public static List<ArrayList<String>> dataCapturePlayer(String url) throws IOException {
		Document document = createDocument1(url);
		Elements eles = document.select(".daTb01").select("tbody").select("tr");//:lt(4).select("li")
		//创建list存放tr
		List<ArrayList<String>> lists = new ArrayList<ArrayList<String>>();
		ArrayList<String> list = null;
		int i = 0;
		for(Element ele:eles) {
			list = new ArrayList<String>();
			if(i==0) {
				for(Element e:ele.select("th:gt(0)")) {
					list.add(e.text());
				}
			}else {
				Elements e = ele.select("td:gt(0)");
				for(int j = 0;j<e.size();j++)
					if(j<2) {
						list.add(e.get(j).select("a").text());
					}else {
						list.add(e.get(j).text());
					}
				}
			i++;
			lists.add(list);
			}
		return lists;
    }
	/**
	 * 爬取所有页面球员信息
	 */
	public static List<TitleAndContent> dataCaptureTeam(String url,Map<String, String> logoMap) throws IOException {
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
		Elements eleh = document.select(".leftList4").select(".daTb01");
		for(int i = 0; i<eleh.size(); i++) {
			//获取每个表格中的每一行
			Elements eleRows = eleh.get(i).select("tbody").select("tr");
			//初始化保存title对象
			titleList = new ArrayList<String>();
			//循环每一行的每一个元素
			contentList = new ArrayList<TreeMap<String,String>>();
			//初始化实体
			entity = new TitleAndContent();
			for(int j = 0;j<eleRows.size()-1;j++) {
				if(j == 0) {
					int num = 0;
					for(Element e:eleRows.get(j).select("th")) {
						//保存title
						if(num == 0 || num == 9) {
							num++;
							continue;
						}else {
							num++;
							titleList.add(e.text());
						}
					}
				}else {
					if(eleRows.get(j).select("td").size()==0) {
						continue;
					}
					//保存内容
					contentRowsMap = new TreeMap<String,String>();
					//预留查询对应字段名
					int num = 0;
					int key = 0;
					for(Element e:eleRows.get(j).select("td")) {
						if(num == 0 || num == 9) {
							num++;
							continue;
						}else {
							num++;
							contentRowsMap.put(key+"", e.text());
							key++;
						}
					}
					//插入保存的表格num(第几个表格)
					contentRowsMap.put((eleRows.get(j).select("td").size()-2)+"", i+"");
					//通过name查询图片并插入图片URL
					//contentRowsMap.put(eleRows.size()+"", JsoupNbaTeamDataCapture.getLogoUrl(contentRowsMap.get("0"),logoMap));
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