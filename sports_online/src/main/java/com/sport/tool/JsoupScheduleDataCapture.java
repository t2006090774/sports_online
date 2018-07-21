package com.sport.tool;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.sport.common.CommonMethod;
import com.sport.entity.dataPage.DataSchedule;
import org.apache.commons.logging.LogFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

/**
 * @author a_kai
 */

public class JsoupScheduleDataCapture {
	
	public static List<DataSchedule> dataCapture(String url,Integer id) throws IOException {
		Document document = createDocument2(url);
		//获取所有赛程
		Elements eles = document.select("#div_saicheng").select("tr[align=center]");
        List<DataSchedule> dataScheduleList = new ArrayList<DataSchedule>();
		DataSchedule dataSchedule = null;
        for(Element ele:eles){
			String secondUrl = ele.select("td:nth-child(8)").select("a").attr("href");
			dataSchedule = JsoupScheduleDataCapture.jsoupSecondPage(secondUrl);
        	//group组别
			String group = ele.select("td:nth-child(3)").select("font").text();
			dataSchedule.setGroupNum(group);
			dataSchedule.setCategoryId(id);
			dataSchedule.setCreateDate(CommonMethod.getCreateTime());
			dataScheduleList.add(dataSchedule);
			System.out.println("解析["+url+"]\n第"+(dataScheduleList.size())+"条数据,剩余"+(eles.size()-dataScheduleList.size())+"条数据待解析！");
        }
        //返回dataHomePageList
		return dataScheduleList;
    }

	public static List<DataSchedule> dataOgCapture(String url,Integer id) throws IOException {
		Document document = createDocument1(url);
		//获取所有赛程
		Elements eles = document.select("#rankcon").select("table").select("tr[align=center]");//:lt(4).select("li")
		List<DataSchedule> dataScheduleList = new ArrayList<DataSchedule>();
		DataSchedule dataSchedule = null;
		for (Element ele : eles) {
			String secondUrl = ele.select("td:nth-child(10)").select("a").attr("href");
			dataSchedule = JsoupScheduleDataCapture.jsoupSecondPage(secondUrl);
			//group组别
			String group = ele.select("td:nth-child(4)").text();
			dataSchedule.setGroupNum(group);
			dataSchedule.setCategoryId(id);
			dataSchedule.setCreateDate(CommonMethod.getCreateTime());
			dataScheduleList.add(dataSchedule);
			System.out.println("解析[" + url + "]\n第" + (dataScheduleList.size()) + "条数据,剩余" + (eles.size() - dataScheduleList.size()) + "条数据待解析！");
		}
		//返回dataHomePageList
		return dataScheduleList;
	}

	public static List<DataSchedule> dataSjbCapture(String url,Integer id) throws IOException {
		Document document = createDocument2(url);
		//获取所有赛程
		Elements eles = document.select("#SUBD1526376597403929");//.select(".tab-2");
		List<DataSchedule> dataScheduleList = new ArrayList<DataSchedule>();
		DataSchedule dataSchedule = null;
		Elements groups = eles.select(".saicheng17933_ind06");
		Elements datas = eles.select(".saicheng17933_ind04");
		//各组别
		for (int i = 0;i<groups.size();i++) {
			//group组别
			String group = groups.get(i).select("div").select("div").select(".gitem").text();

			Elements rowEles = datas.get(i).select("table").select("tr:gt(0)");//.select("tr")
			for(Element rowEle:rowEles){
				dataSchedule = new DataSchedule();
				//比分
				String score = rowEle.select("td:nth-child(3)").select("i").text();
				dataSchedule.setScore(score);
				//时间
				String time = rowEle.select("td:nth-child(1)").text()+rowEle.select("td:nth-child(2)").text();
				dataSchedule.setTime(time);
				//左队logo
				String teamLeftLogo = rowEle.select("td:nth-child(3)").select("a:nth-child(1)").select("img").attr("src");
				dataSchedule.setTeamLeftLogo(teamLeftLogo);
				//左队name
				String teamLeftName = rowEle.select("td:nth-child(3)").select("a:nth-child(2)").text();
				dataSchedule.setTeamLeftName(teamLeftName);
				//右队logo
				String teamRightLogo = rowEle.select("td:nth-child(3)").select("a:nth-child(5)").select("img").attr("src");
				dataSchedule.setTeamRightLogo(teamRightLogo);
				//右队name
				String teamRightName = rowEle.select("td:nth-child(3)").select("a:nth-child(4)").text();
				dataSchedule.setTeamRightName(teamRightName);

				dataSchedule.setGroupNum(group);
				dataSchedule.setCategoryId(id);
				dataSchedule.setCreateDate(CommonMethod.getCreateTime());
				dataScheduleList.add(dataSchedule);
			}
			System.out.println("解析[" + url + "]\n第" + i + "条数据,剩余" + (eles.size() - dataScheduleList.size()) + "条数据待解析！");
		}
		//dataScheduleList
		return dataScheduleList;
	}
	
	/**
	 * 用Jsoup解析二级页面并保存数据
	 * @param url
	 * @return	Document
	 * @throws IOException
	 */
	public static DataSchedule jsoupSecondPage(String url) throws IOException {
		if(url == null||url == ""){
			return null;
		}
		Document secondDocument = JsoupScheduleDataCapture.createDocument1(url);
		DataSchedule dataSecondaryPage = new DataSchedule();
		//取区域
		Elements eles = secondDocument.select("#main");
		//比分
		String score = eles.select("div.match").select("table").select("tbody").select("tr").select("td:nth-child(3)").select("div").text();
		dataSecondaryPage.setScore(score);
		//时间
		String time = eles.select("div.match").select("table").select("tbody").select("tr").select("td:nth-child(3)").select("span.hui.12").text();
		dataSecondaryPage.setTime(time);
		//左队logo
		String teamLeftLogo = eles.select("div.match").select("table").select("tbody").select("tr").select("td:nth-child(1)").select("img").attr("src");
		dataSecondaryPage.setTeamLeftLogo(teamLeftLogo);
		//左队name
		String teamLeftName = eles.select("div.match").select("table").select("tbody").select("tr").select("td:nth-child(2)").select("h1").select("a").text();
		dataSecondaryPage.setTeamLeftName(teamLeftName);
		//左队EnglishName
		String teamLeftEnglishName = eles.select("div.match").select("table").select("tbody").select("tr").select("td:nth-child(2)").select("h2").select("a").text();
		dataSecondaryPage.setTeamLeftEnglishName(teamLeftEnglishName);

		//右队logo
		String teamRightLogo = eles.select("div.match").select("table").select("tbody").select("tr").select("td:nth-child(5)").select("img").attr("src");
		dataSecondaryPage.setTeamRightLogo(teamRightLogo);
		//右队name
		String teamRightName = eles.select("div.match").select("table").select("tbody").select("tr").select("td:nth-child(4)").select("h1").select("a").text();
		dataSecondaryPage.setTeamRightName(teamRightName);
		//右队EnglishName
		String teamRightEnglishName = eles.select("div.match").select("table").select("tbody").select("tr").select("td:nth-child(4)").select("h2").select("a").text();
		dataSecondaryPage.setTeamRightEnglishName(teamRightEnglishName);
		//content
		Elements contents = eles.select("div.block.border").select("div.f13").select("p");
		StringBuilder content = new StringBuilder();
		for(Element e:contents){
			if("strong".equals(e.childNode(0).nodeName())){
				content.append(e.select("strong").text());
			}else if("img".equals(e.childNode(0).nodeName())){
				content.append(e.select("img").attr("src"));
			}else {
				content.append(e.text());
			}
			content.append("@@@@@");
		}
		dataSecondaryPage.setContent(content.toString());
		return dataSecondaryPage;
	}
	
	/**
	 * 将地址解析为Document对象
	 * @param url
	 * @return	Document
	 * @throws IOException
	 */
	public static Document createDocument2(String url) throws IOException {
		//构造一个webClient 模拟Chrome 浏览器
		@SuppressWarnings("resource")
		WebClient webClient = new WebClient(BrowserVersion.CHROME);
		//屏蔽日志信息
		LogFactory.getFactory().setAttribute("org.apache.commons.logging.Log",
		        "org.apache.commons.logging.impl.NoOpLog");
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
		
		//未执行异步加载数据
        /*Document document=Jsoup.connect(url)
                //模拟火狐浏览器
                .userAgent("Mozilla/4.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0)")
                .get();*/
		return document;
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