package io.renren.controller;

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

import io.renren.entity.CahgLetterFlowEntity;
import io.renren.service.CahgLetterFlowService;
import io.renren.utils.PageUtils;
import io.renren.utils.R;
import io.renren.utils.ShiroUtils;


/**
 * 
 * 
 * @author 
 * @email 
 * @date 2017-08-18 13:41:20
 */
@Controller
@RequestMapping("cahgletterflow")
public class CahgLetterFlowController {
	@Autowired
	private CahgLetterFlowService cahgLetterFlowService;
	
	@RequestMapping("/cahgletterflow.html")
	public String list(){
		return "cahgletterflow/cahgletterflow.html";
	}
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("cahgletterflow:list")
	public R list(Integer page, Integer limit){
		Map<String, Object> map = new HashMap<>();
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		map.put("userId", ShiroUtils.getUserId());
		
		//查询列表数据
		List<CahgLetterFlowEntity> cahgLetterFlowList = cahgLetterFlowService.queryList(map);
		int total = cahgLetterFlowService.queryTotal(map);
		
		PageUtils pageUtil = new PageUtils(cahgLetterFlowList, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{letterFlowId}")
	@RequiresPermissions("cahgletterflow:info")
	public R info(@PathVariable("letterFlowId") Integer letterFlowId){
		CahgLetterFlowEntity cahgLetterFlow = cahgLetterFlowService.queryObject(letterFlowId);
		
		return R.ok().put("cahgLetterFlow", cahgLetterFlow);
	}
	
	//未处理信封数量
	@ResponseBody
	@RequestMapping("/queryUnDealTotal")
	public R queryUnDealTotal(){
		Map<String, Object> map =new HashMap<>();
		map.put("userId", ShiroUtils.getUserId());
		int count = cahgLetterFlowService.queryUnDealTotal(map);
		return R.ok().put("count", count);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
//	@RequiresPermissions("cahgletterflow:save")
	public R save(@RequestBody CahgLetterFlowEntity cahgLetterFlow){
		cahgLetterFlow.setCreateUserId(ShiroUtils.getUserId());
		cahgLetterFlow.setStatus(0);
		cahgLetterFlowService.save(cahgLetterFlow);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("cahgletterflow:update")
	public R update(@RequestBody CahgLetterFlowEntity cahgLetterFlow){
		cahgLetterFlowService.update(cahgLetterFlow);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete")
	@RequiresPermissions("cahgletterflow:delete")
	public R delete(@RequestBody Integer[] letterFlowIds){
		cahgLetterFlowService.deleteBatch(letterFlowIds);
		
		return R.ok();
	}
	
	@ResponseBody
	@RequestMapping("/hadDeal")
	public R hadDeal(@RequestBody Integer[] letterFlowIds){
		cahgLetterFlowService.hadDeal(letterFlowIds);
		
		return R.ok();
	}
	
}
