package com.sport.controller.htmlController;

import com.sport.common.CommonMethod;
import com.sport.entity.dataPage.DataSecondaryPage;
import com.sport.service.dataPage.IDataPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Map;

/**
 * @author a_kai
 */
@Controller
@RequestMapping("/news")
public class NewsController {
	
	/*@Autowired
	private IHtmlService htmlService;*/
    @Autowired
    private IDataPageService dataPageService;


    /**
     * 跳转新闻首页html
     */
    @RequestMapping(value = "/index")
    public ModelAndView index() {
        //return new ModelAndView(new RedirectView("http://www.baidu.com"));
        return new ModelAndView("news/news_index");
    }

    /**
     * 跳转新闻二级html
     */
    @RequestMapping(value = "/second/{pid}",method=RequestMethod.GET)
    public String second(@PathVariable("pid")Integer pid,Map map) {
        //查询列表数据
        DataSecondaryPage entity = dataPageService.getSecondPageData(pid);
        map.put("pageData",CommonMethod.pojoTransformJson(entity));
        return "news/news_second";
    }



}