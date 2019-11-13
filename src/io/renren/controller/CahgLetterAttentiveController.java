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

import io.renren.entity.CahgLetterAttentiveEntity;
import io.renren.service.CahgLetterAttentiveService;
import io.renren.utils.PageUtils;
import io.renren.utils.R;


/**
 * 
 * 
 * @author 
 * @email 
 * @date 2017-11-24 10:35:30
 */
@Controller
@RequestMapping("cahgletterattentive")
public class CahgLetterAttentiveController {
	@Autowired
	private CahgLetterAttentiveService cahgLetterAttentiveService;
	
	@RequestMapping("/cahgletterattentive.html")
	public String list(){
		return "cahgletterattentive/cahgletterattentive.html";
	}
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("cahgletterattentive:list")
	public R list(Integer page, Integer limit){
		Map<String, Object> map = new HashMap<>();
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		
		//查询列表数据
		List<CahgLetterAttentiveEntity> cahgLetterAttentiveList = cahgLetterAttentiveService.queryList(map);
		int total = cahgLetterAttentiveService.queryTotal(map);
		
		PageUtils pageUtil = new PageUtils(cahgLetterAttentiveList, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{letterAttentiveId}")
//	@RequiresPermissions("cahgletterattentive:info")
	public R info(@PathVariable("letterAttentiveId") Integer letterAttentiveId){
		CahgLetterAttentiveEntity cahgLetterAttentive = cahgLetterAttentiveService.queryObject(letterAttentiveId);
		
		return R.ok().put("cahgLetterAttentive", cahgLetterAttentive);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("cahgletterattentive:save")
	public R save(@RequestBody CahgLetterAttentiveEntity cahgLetterAttentive){
		cahgLetterAttentiveService.save(cahgLetterAttentive);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
///	@RequiresPermissions("cahgletterattentive:update")
	public R update(@RequestBody CahgLetterAttentiveEntity cahgLetterAttentive){
		cahgLetterAttentiveService.update(cahgLetterAttentive);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
/*	@ResponseBody
	@RequestMapping("/delete")
	@RequiresPermissions("cahgletterattentive:delete")
	public R delete(@RequestBody Integer[] letterAttentiveIds){
		cahgLetterAttentiveService.deleteBatch(letterAttentiveIds);
		
		return R.ok();
	}
	*/
}
