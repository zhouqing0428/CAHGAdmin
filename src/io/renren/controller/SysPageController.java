package io.renren.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 系统页面视图
 * 
 * @author 
 * @email 
 * @date 
 */
@Controller
public class SysPageController {
	
	@RequestMapping("sys/{url}.html")
	public String page(@PathVariable("url") String url){
		return "sys/" + url + ".html";
	}
	
	@RequestMapping("sys/{url}.jsp")
	public String pages(@PathVariable("url") String url){
		return "sys/" + url + ".jsp";
	}
	
	@RequestMapping("news/{url}.html")
	public String news(@PathVariable("url") String url){
		return "news/" + url + ".html";
	}
	
	@RequestMapping("oa/{url}.html")
	public String oa(@PathVariable("url") String url){
		return "oa/" + url + ".html";
	}
	
	@RequestMapping("web/{url}.html")
	public String web(@PathVariable("url") String url){
		return "web/" + url + ".html";
	}
}
