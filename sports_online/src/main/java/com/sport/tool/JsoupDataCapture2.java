package com.sport.tool;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import org.apache.commons.logging.LogFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.transaction.annotation.Transactional;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.sport.common.CommonMethod;
import com.sport.entity.Category;
import com.sport.entity.ParserRule;
import com.sport.entity.dataPage.DataHomePage;
import com.sport.entity.dataPage.DataSecondaryPage;

/**
 * @author a_kai
 */

public class JsoupDataCapture2 {
	
	
	@Transactional
	public static List<DataHomePage> dataCapture(Category category) throws IOException {
		//加载document
		Document document = null;
		//判断是否为异步数据页面1.先加载js获取
		if(category.getIsAsynchronous()==1) {
			document = createDocumentAsynchronous(category.getCategoryDataUrl());
		}else {
			document = createDocument(category.getCategoryDataUrl());
		}
		//获取homePageData并保存list
		return getDataHomePage(document,category);
    }
	
	/**
	 * 通过规则获取主页值
	 * @throws IOException 
	 */
	public static List<DataHomePage> getDataHomePage(Document document,Category category) throws IOException {
		//初始化需要的数据
		List<ParserRule> prList = category.getParserRuleList();
		Integer categoryId = category.getCategoryId();
		Integer elesSelectorNum = category.getElementsSelectorNum();
		List<DataHomePage> dataHomePageList = new ArrayList<DataHomePage>();
		
		//首页信息Elements规则解析
		String[] sArray = prList.get(0).getSelector().split("\\+");
		Elements eles = null;
		//获取Elements
		for(int i = 0;i<elesSelectorNum;i++) {
			if(i==0) {
				//第一次加载document
				eles = selectMethod(document,sArray[i]);
			}else {
				//加载eles
				eles = selectElementsMethod(eles,sArray[i]);
			}
		}
		DataHomePage dataHomePage = null;
		//遍历首页数据
		for(Element ele:eles) {
			//存放每次获取的数据
			dataHomePage = new DataHomePage();
			//判断为空直接跳过分析该条规则
			for(ParserRule pr:prList) {
				//为空跳过循环
				if(pr.getColumnName()!=null&&pr.getColumnName()!="") {
					//分别解析字段规则
					//1.pictureUrl
					if("pictureUrl".equals(pr.getColumnName())) {
						sArray = pr.getSelector().split("\\+");
						dataHomePage.setPictureUrl(getColumnsValue(elesSelectorNum,sArray,ele,pr.getRequestHeader()));
					}
					//2.取title
					else if("title".equals(pr.getColumnName())) {
						sArray = pr.getSelector().split("\\+");
						dataHomePage.setTitle(getColumnsValue(elesSelectorNum,sArray,ele,pr.getRequestHeader()));
					}
					//3.取newsTime
					else if("newsTime".equals(pr.getColumnName())) {
						sArray = pr.getSelector().split("\\+");
						dataHomePage.setNewsTime(getColumnsValue(elesSelectorNum,sArray,ele,pr.getRequestHeader()));
					}
					//4.获取二级页面地址
					else if("secondUrl".equals(pr.getColumnName())) {
						sArray = pr.getSelector().split("\\+");
						dataHomePage.setSecondUrl(getColumnsValue(elesSelectorNum,sArray,ele,pr.getRequestHeader()));
					}
				}else {
					continue;
				}
			}
			dataHomePage.setCategoryId(categoryId);
			dataHomePage.setCreateDate(CommonMethod.getCreateTime());
			//5.获取二级页面数据
			dataHomePage.setDataSecondaryPage(jsoupSecondPage(dataHomePage,category));
			dataHomePageList.add(dataHomePage);
		}
		return dataHomePageList;
	}
	
	/**
	 * 用Jsoup解析二级页面并保存数据
	 */
	public static DataSecondaryPage jsoupSecondPage(DataHomePage dataHomePage,Category category) throws IOException {
		//加载二级页面url
		Document secondDocument = null;
		try {
			secondDocument = JsoupDataCapture2.createDocument(dataHomePage.getSecondUrl());
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		//初始化需要的数据
		List<ParserRule> prList = category.getParserRuleList();
		//存放每次获取的数据
		DataSecondaryPage dataSecondaryPage = new DataSecondaryPage();
		//首页信息Elements规则解析
		String[] sArray = null;
		for(ParserRule pr:prList) {
			//为空跳过循环-判断为空直接跳过分析该条规则
			if(pr.getColumnName()!=null&&pr.getColumnName()!="") {
				//分别解析字段规则
				//1.titleSecond
				if("titleSecond".equals(pr.getColumnName())) {
					sArray = pr.getSelector().split("\\+");
					dataSecondaryPage.setTitleSecond(getColumnValue(secondDocument,sArray));
				}
				//2.取promulgatorSecond
				else if("promulgatorSecond".equals(pr.getColumnName())) {
					sArray = pr.getSelector().split("\\+");
					dataSecondaryPage.setPromulgatorSecond(getColumnValue(secondDocument,sArray));
				}
				//3.取newsTimeSecond
				else if("newsTimeSecond".equals(pr.getColumnName())) {
					sArray = pr.getSelector().split("\\+");
					dataSecondaryPage.setNewsTimeSecond(getColumnValue(secondDocument,sArray));
				}
				//4.取pictureUrlSecond
				else if("pictureUrlSecond".equals(pr.getColumnName())) {
					sArray = pr.getSelector().split("\\+");
					dataSecondaryPage.setPictureUrlSecond(getColumnValues(secondDocument,sArray,pr.getRequestHeader()));
				}
				//5.取contentSecond
				else if("contentSecond".equals(pr.getColumnName())) {
					sArray = pr.getSelector().split("\\+");
					dataSecondaryPage.setContentSecond(getColumnValues(secondDocument,sArray,pr.getRequestHeader()));
				}
			}else {
				continue;
			}
		}
		
		return dataSecondaryPage;
	}
	
	/**
	 * 通过规则循环获取图片地址和内容
	 */
	public static List<String> getColumnValues(Document document,String[] sArray,String requestHeader) {
		//解析规则
		Elements eles = null;
		List<String> list = new ArrayList<String>();
		for(int i = 0;i<sArray.length;i++) {//String s:sArray
			if(i!=sArray.length-1) {
				//拼接规则
				if(i==0) {
					eles = selectMethod(document,sArray[i]);
				}else {
					eles = selectElementsMethod(eles,sArray[i]);
				}
			}else {
				//取值
				if("src".equals(sArray[i])) {
					for(Element ele:eles) {
						//请求头不为空时拼接
						if(requestHeader!=null) {
							list.add(requestHeader+ele.attr("src"));
						}else {
							list.add(ele.attr("src"));
						}
					}
					return list;
				}else if("text".equals(sArray[i])) {
					for(Element ele:eles) {
						list.add(ele.text());
					}
					return list;
				}else if("href".equals(sArray[i])) {
					for(Element ele:eles) {
						//请求头不为空时拼接
						if(requestHeader!=null) {
							list.add(requestHeader+ele.attr("href"));
						}else {
							list.add(ele.attr("href"));
						}
					}
					return list;
				}else {
					return list;
				}
			}
		}
		return list;
	}
	
	/**
	 * 通过规则获取值
	 */
	public static String getColumnValue(Document document,String[] sArray) {
		//解析规则
		Elements eles = null;
		for(int i = 0;i<sArray.length;i++) {//String s:sArray
			if(i!=sArray.length-1) {
				//拼接规则
				if(i==0) {
					eles = selectMethod(document,sArray[i]);
				}else {
					eles = selectElementsMethod(eles,sArray[i]);
				}
			}else {
				//取值
				if("src".equals(sArray[i])) {
					return eles.attr("src");
				}else if("text".equals(sArray[i])) {
					return eles.text();
				}else if("href".equals(sArray[i])) {
					return eles.attr("href");
				}else {
					return "";
				}
			}
		}
		return "";
	}
	
	/**
	 * 拼接select
	 */
	public static Elements selectMethod(Document document,String s) {
		Elements elements = document.select(s);
		return elements;
	}
	
	/**
	 * 拼接select
	 */
	public static Elements selectElementsMethod(Elements eles,String s) {
		eles = eles.select(s);
		return eles;
	}
	
	/**
	 * 通过规则获取Home页面值
	 */
	public static String getColumnsValue(Integer index,String[] sArray,Element ele,String requestHeader) {
		Elements tempElements = null;
		for(int i = index;i<sArray.length;i++) {//String s:sArray
			if(i!=sArray.length-1) {
				//拼接规则
				if(i==index) {
					tempElements = ele.select(sArray[i]);
				}else {
					tempElements = tempElements.select(sArray[i]);
				}
			}else {	
				//取值
				if("src".equals(sArray[i])) {
					if(requestHeader!=null) {
						return requestHeader+tempElements.attr("src");
					}else {
						return tempElements.attr("src");
					}
				}else if("text".equals(sArray[i])) {
					return tempElements.text();
				}else if("href".equals(sArray[i])) {
					if(requestHeader!=null) {
						return requestHeader+tempElements.attr("href");
					}else {
						return tempElements.attr("href");
					}
				}else {
					return "";
				}
			}
		}
		return "";
	}
	
	/**
	 * 将地址解析为Document对象
	 * @param url
	 * @return	Document
	 * @throws IOException
	 */
	public static Document createDocument(String url) throws IOException {
		//获取编辑推荐页
        Document document=Jsoup.connect(url)
                //模拟火狐浏览器
                .userAgent("Mozilla/4.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0)")
                .get();
		return document;
	}
	
	/**
	 * 将地址解析为Document对象(异步数据网页)
	 */
	public static Document createDocumentAsynchronous(String url) throws IOException {
		//构造一个webClient 模拟Chrome 浏览器
		WebClient webClient = new WebClient(BrowserVersion.CHROME);
		//屏蔽日志信息
		LogFactory.getFactory().setAttribute("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.NoOpLog");
		java.util.logging.Logger.getLogger("com.gargoylesoftware").setLevel(Level.OFF);
		//支持JavaScript
		webClient.getOptions().setJavaScriptEnabled(true);
		webClient.getOptions().setCssEnabled(false);
		webClient.getOptions().setActiveXNative(false);
		webClient.getOptions().setCssEnabled(false);
		webClient.getOptions().setThrowExceptionOnScriptError(false); // js运行错误时，是否抛出异常
		webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
		webClient.getOptions().setTimeout(5000);
		HtmlPage rootPage = webClient.getPage(url);
		//设置一个运行JavaScript的时间
		webClient.waitForBackgroundJavaScript(5000);
		String html = rootPage.asXml();
		//获取编辑推荐页
		Document document = Jsoup.parse(html);
		webClient.getCurrentWindow().getJobManager().removeAllJobs();  
		webClient.close();
		return document;
	}
	
	
}