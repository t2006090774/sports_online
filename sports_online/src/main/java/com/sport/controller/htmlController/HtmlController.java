package com.sport.controller.htmlController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author a_kai
 */
@Controller  
@RequestMapping("/html")
public class HtmlController {
	
	/*@Autowired
	private IHtmlService htmlService;*/

    /**
     * 跳转html
     */
    @RequestMapping(value="/pokerOne")
    public String login() {
        return "poker/gameOne";
    }

    /**
     * 跳转html
     */
    @RequestMapping(value="/index")
    public String index() {
        return "poker/index";
    }

}