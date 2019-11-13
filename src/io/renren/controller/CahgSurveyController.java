package io.renren.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.stereotype.Controller;

import io.renren.entity.CahgSurveyEntity;
import io.renren.service.CahgSurveyService;
import io.renren.utils.PageUtils;
import io.renren.utils.R;


/**
 * 
 * 
 * @author 
 * @email 
 * @date 2017-08-03 11:38:38
 */
@Controller
@RequestMapping("cahgsurvey")
public class CahgSurveyController {
	@Autowired
	private CahgSurveyService cahgSurveyService;
	
	@RequestMapping("/cahgsurvey.html")
	public String list(){
		return "cahgsurvey/cahgsurvey.html";
	}
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("cahgsurvey:list")
	public R list(Integer page, Integer limit){
		Map<String, Object> map = new HashMap<>();
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		
		//查询列表数据
		List<CahgSurveyEntity> cahgSurveyList = cahgSurveyService.queryList(map);
		int total = cahgSurveyService.queryTotal(map);
		
		PageUtils pageUtil = new PageUtils(cahgSurveyList, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{surveyId}")
	@RequiresPermissions("cahgsurvey:info")
	public R info(@PathVariable("surveyId") Integer surveyId){
		CahgSurveyEntity cahgSurvey = cahgSurveyService.queryObject(surveyId);
		
		return R.ok().put("cahgSurvey", cahgSurvey);
	}
	
	//卷名问题
	@RequestMapping("/questionInfo")
	public String questionInfo(String surveyId,HttpServletRequest request){
		System.out.println("surveyId="+surveyId);
		request.setAttribute("surveyId", surveyId);
		return "web/cahgsurveyquestion.jsp";
	}
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("cahgsurvey:save")
	public R save(@RequestBody CahgSurveyEntity cahgSurvey){
		cahgSurveyService.save(cahgSurvey);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("cahgsurvey:update")
	public R update(@RequestBody CahgSurveyEntity cahgSurvey){
		cahgSurveyService.update(cahgSurvey);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete")
	@RequiresPermissions("cahgsurvey:delete")
	public R delete(@RequestBody Integer[] surveyIds){
		cahgSurveyService.deleteBatch(surveyIds);
		
		return R.ok();
	}
	
}
