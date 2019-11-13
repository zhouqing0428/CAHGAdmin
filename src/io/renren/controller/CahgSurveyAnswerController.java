package io.renren.controller;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.stereotype.Controller;

import io.renren.entity.CahgSurveyAnswerEntity;
import io.renren.service.CahgSurveyAnswerService;
import io.renren.utils.PageUtils;
import io.renren.utils.R;


/**
 * 
 * 
 * @author 
 * @email 
 * @date 2017-08-03 18:09:15
 */
@Controller
@RequestMapping("cahgsurveyanswer")
public class CahgSurveyAnswerController {
	@Autowired
	private CahgSurveyAnswerService cahgSurveyAnswerService;
	
	@RequestMapping("/cahgsurveyanswer.html")
	public String list(){
		return "cahgsurveyanswer/cahgsurveyanswer.html";
	}
	
	/**
	 * 列表   全部显示，不分页
	 */
	@ResponseBody
	@RequestMapping("/list")
	//@RequiresPermissions("cahgsurveyanswer:list")
	public R list(String surveyQuestionId,String pollSum,Integer page, Integer limit){
		Map<String, Object> map = new HashMap<>();
	/*	map.put("offset", (page - 1) * limit);
		map.put("limit", limit);*/
		map.put("surveyQuestionId", surveyQuestionId);
		
		//查询列表数据
		List<CahgSurveyAnswerEntity> cahgSurveyAnswerList = cahgSurveyAnswerService.queryList(map);
	//	int total = cahgSurveyAnswerService.queryTotal(map);
		int total=0;
		CahgSurveyAnswerEntity anser=new CahgSurveyAnswerEntity();
		DecimalFormat df = new DecimalFormat("0.00%");  
		if(pollSum!=null&&!pollSum.isEmpty()){
			total=Integer.parseInt(pollSum);
			if(total!=0){
				for(int i=0;i<cahgSurveyAnswerList.size();i++){
					double temp=(double)cahgSurveyAnswerList.get(i).getPoll()/total;
					cahgSurveyAnswerList.get(i).setPercent(df.format(temp));
				}
			}else{
				for(int i=0;i<cahgSurveyAnswerList.size();i++){
					cahgSurveyAnswerList.get(i).setPercent(df.format(1));
				}
			}
		    
		}
		
		PageUtils pageUtil = new PageUtils(cahgSurveyAnswerList, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{surveyAnswerId}")
	//@RequiresPermissions("cahgsurveyanswer:info")
	public R info(@PathVariable("surveyAnswerId") Integer surveyAnswerId){
		CahgSurveyAnswerEntity cahgSurveyAnswer = cahgSurveyAnswerService.queryObject(surveyAnswerId);
		
		return R.ok().put("cahgSurveyAnswer", cahgSurveyAnswer);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
//	@RequiresPermissions("cahgsurveyanswer:save")
	public R save(@RequestBody CahgSurveyAnswerEntity cahgSurveyAnswer){
		cahgSurveyAnswerService.save(cahgSurveyAnswer);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
//	@RequiresPermissions("cahgsurveyanswer:update")
	public R update(@RequestBody CahgSurveyAnswerEntity cahgSurveyAnswer){
		cahgSurveyAnswerService.update(cahgSurveyAnswer);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete")
//	@RequiresPermissions("cahgsurveyanswer:delete")
	public R delete(@RequestBody Integer[] surveyAnswerIds){
		cahgSurveyAnswerService.deleteBatch(surveyAnswerIds);
		
		return R.ok();
	}
	
}
