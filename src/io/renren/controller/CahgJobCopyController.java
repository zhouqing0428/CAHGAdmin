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

import io.renren.entity.CahgJobCopyEntity;
import io.renren.service.CahgJobCopyService;
import io.renren.utils.PageUtils;
import io.renren.utils.R;
import io.renren.utils.ShiroUtils;


/**
 * 
 * 
 * @author 
 * @email 
 * @date 2018-03-21 15:34:27
 */
@Controller
@RequestMapping("cahgjobcopy")
public class CahgJobCopyController {
	@Autowired
	private CahgJobCopyService cahgJobCopyService;
	
	@RequestMapping("/cahgjobcopy.html")
	public String list(){
		return "cahgjobcopy/cahgjobcopy.html";
	}
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("cahgjobcopy:list")
	public R list(Integer page, Integer limit){
		Map<String, Object> map = new HashMap<>();
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		map.put("userId", ShiroUtils.getUserId());
		
		//查询列表数据
		List<CahgJobCopyEntity> cahgJobCopyList = cahgJobCopyService.queryList(map);
		int total = cahgJobCopyService.queryTotal(map);
		
		PageUtils pageUtil = new PageUtils(cahgJobCopyList, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{jobCopyId}")
	@RequiresPermissions("cahgjobcopy:info")
	public R info(@PathVariable("jobCopyId") Integer jobCopyId){
		CahgJobCopyEntity cahgJobCopy = cahgJobCopyService.queryObject(jobCopyId);
		
		return R.ok().put("cahgJobCopy", cahgJobCopy);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
//	@RequiresPermissions("cahgjobcopy:save")
	public R save(@RequestBody CahgJobCopyEntity cahgJobCopy){
		cahgJobCopy.setCreateUserId(ShiroUtils.getUserId());

		cahgJobCopyService.save(cahgJobCopy);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("cahgjobcopy:update")
	public R update(@RequestBody CahgJobCopyEntity cahgJobCopy){
		cahgJobCopy.setLastUpdateUserId(ShiroUtils.getUserId());

		cahgJobCopyService.update(cahgJobCopy);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete")
	@RequiresPermissions("cahgjobcopy:delete")
	public R delete(@RequestBody Integer[] jobCopyIds){
		cahgJobCopyService.deleteBatch(jobCopyIds);
		
		return R.ok();
	}
	
}
