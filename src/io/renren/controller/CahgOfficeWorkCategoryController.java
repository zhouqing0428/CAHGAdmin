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

import io.renren.entity.CahgOfficeWorkCategoryEntity;
import io.renren.service.CahgOfficeWorkCategoryService;
import io.renren.utils.PageUtils;
import io.renren.utils.R;
import io.renren.utils.ShiroUtils;


/**
 * 
 * 
 * @author 
 * @email 
 * @date 2017-12-08 16:43:42
 */
@Controller
@RequestMapping("cahgofficeworkcategory")
public class CahgOfficeWorkCategoryController {
	@Autowired
	private CahgOfficeWorkCategoryService cahgOfficeWorkCategoryService;
	
	@RequestMapping("/cahgofficeworkcategory.html")
	public String list(){
		return "cahgofficeworkcategory/cahgofficeworkcategory.html";
	}
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("cahgofficeworkcategory:list")
	public R list(Integer page, Integer limit){
		Map<String, Object> map = new HashMap<>();
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		
		//查询列表数据
		List<CahgOfficeWorkCategoryEntity> cahgOfficeWorkCategoryList = cahgOfficeWorkCategoryService.queryList(map);
		int total = cahgOfficeWorkCategoryService.queryTotal(map);
		
		PageUtils pageUtil = new PageUtils(cahgOfficeWorkCategoryList, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	
	@ResponseBody
	@RequestMapping("/selectList")
	public R selectList(Integer page, Integer limit){
		Map<String, Object> map = new HashMap<>();
		
		//查询列表数据
		List<CahgOfficeWorkCategoryEntity> list = cahgOfficeWorkCategoryService.queryList(map);
		
		return R.ok().put("list", list);
	}
	
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{officeWorkCategoryId}")
	@RequiresPermissions("cahgofficeworkcategory:info")
	public R info(@PathVariable("officeWorkCategoryId") Integer officeWorkCategoryId){
		CahgOfficeWorkCategoryEntity cahgOfficeWorkCategory = cahgOfficeWorkCategoryService.queryObject(officeWorkCategoryId);
		
		return R.ok().put("cahgOfficeWorkCategory", cahgOfficeWorkCategory);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("cahgofficeworkcategory:save")
	public R save(@RequestBody CahgOfficeWorkCategoryEntity cahgOfficeWorkCategory){
		cahgOfficeWorkCategory.setCreateUserId(ShiroUtils.getUserId());
		cahgOfficeWorkCategoryService.save(cahgOfficeWorkCategory);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("cahgofficeworkcategory:update")
	public R update(@RequestBody CahgOfficeWorkCategoryEntity cahgOfficeWorkCategory){
		cahgOfficeWorkCategory.setLastUpdateUserId(ShiroUtils.getUserId());
		cahgOfficeWorkCategoryService.update(cahgOfficeWorkCategory);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete")
	@RequiresPermissions("cahgofficeworkcategory:delete")
	public R delete(@RequestBody Integer[] officeWorkCategoryIds){
		cahgOfficeWorkCategoryService.deleteBatch(officeWorkCategoryIds);
		
		return R.ok();
	}
	
}
