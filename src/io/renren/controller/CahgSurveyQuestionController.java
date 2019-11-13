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

import io.renren.entity.CahgSurveyQuestionEntity;
import io.renren.service.CahgSurveyQuestionService;
import io.renren.utils.PageUtils;
import io.renren.utils.R;
import io.renren.utils.ShiroUtils;


/**
 * 
 * 
 * @author 
 * @email 
 * @date 2017-08-03 15:41:00
 */
@Controller
@RequestMapping("cahgsurveyquestion")
public class CahgSurveyQuestionController {
	@Autowired
	private CahgSurveyQuestionService cahgSurveyQuestionService;
	
	@RequestMapping("/cahgsurveyquestion.html")
	public String list(){
		return "cahgsurveyquestion/cahgsurveyquestion.html";
	}
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("cahgsurveyquestion:list")
	public R list(String surveyId,Integer page, Integer limit){
		Map<String, Object> map = new HashMap<>();
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		map.put("surveyId", surveyId);
		
		//查询列表数据
		List<CahgSurveyQuestionEntity> cahgSurveyQuestionList = cahgSurveyQuestionService.queryList(map);
		int total = cahgSurveyQuestionService.queryTotal(map);
		
		PageUtils pageUtil = new PageUtils(cahgSurveyQuestionList, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{surveyQuestionId}")
	@RequiresPermissions("cahgsurveyquestion:info")
	public R info(@PathVariable("surveyQuestionId") Integer surveyQuestionId){
		CahgSurveyQuestionEntity cahgSurveyQuestion = cahgSurveyQuestionService.queryObject(surveyQuestionId);
		
		return R.ok().put("cahgSurveyQuestion", cahgSurveyQuestion);
	}
	
	//问题选项
	@RequestMapping("/answerInfo")
	public String answerInfo(String surveyQuestionId,HttpServletRequest request){
		CahgSurveyQuestionEntity surveyQuestion=null;
		Integer pollSum=0;
		if(surveyQuestionId!=null&&!surveyQuestionId.isEmpty()){
			int questionId=Integer.parseInt(surveyQuestionId);
			surveyQuestion = cahgSurveyQuestionService.queryObject(questionId);
			pollSum=cahgSurveyQuestionService.queryPollSum(questionId);
			request.setAttribute("pollSum", pollSum);
			request.setAttribute("surveyQuestionId", surveyQuestionId);
				request.setAttribute("surveyQuestion", surveyQuestion);
		}
		return "web/cahgsurveyanswer.jsp";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("cahgsurveyquestion:save")
	public R save(@RequestBody CahgSurveyQuestionEntity cahgSurveyQuestion){
		cahgSurveyQuestion.setCreateUserId(ShiroUtils.getUserId());
		cahgSurveyQuestionService.save(cahgSurveyQuestion);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("cahgsurveyquestion:update")
	public R update(@RequestBody CahgSurveyQuestionEntity cahgSurveyQuestion){
		cahgSurveyQuestion.setLastUpdateUserId(ShiroUtils.getUserId());
		cahgSurveyQuestionService.update(cahgSurveyQuestion);
		
		return R.ok();
	}
	
	/**
	 *首页显示
	 */
	
	@ResponseBody
	@RequestMapping("/indexShow/{surveyQuestionId}")
	@RequiresPermissions("cahgsurveyquestion:indexShow")
	public R indexShow(@PathVariable("surveyQuestionId") Integer surveyQuestionId){
		cahgSurveyQuestionService.unIndexShow(); //取消首页显示
	    cahgSurveyQuestionService.indexShow(surveyQuestionId);
	    
		return R.ok();
	}
	
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete")
	@RequiresPermissions("cahgsurveyquestion:delete")
	public R delete(@RequestBody Integer[] surveyQuestionIds){
		cahgSurveyQuestionService.deleteBatch(surveyQuestionIds);
		
		return R.ok();
	}
	
}
