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

import io.renren.entity.CahgWorkTableCategoryEntity;
import io.renren.service.CahgWorkTableCategoryService;
import io.renren.utils.PageUtils;
import io.renren.utils.R;
import io.renren.utils.ShiroUtils;


/**
 * 
 * 
 * @author 
 * @email 
 * @date 2017-08-15 11:46:04
 */
@Controller
@RequestMapping("cahgworktablecategory")
public class CahgWorkTableCategoryController {
	@Autowired
	private CahgWorkTableCategoryService cahgWorkTableCategoryService;
	
	@RequestMapping("/cahgworktablecategory.html")
	public String list(){
		return "cahgworktablecategory/cahgworktablecategory.html";
	}
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("cahgworktablecategory:list")
	public R list(Integer page, Integer limit){
		Map<String, Object> map = new HashMap<>();
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		
		//查询列表数据
		List<CahgWorkTableCategoryEntity> cahgWorkTableCategoryList = cahgWorkTableCategoryService.queryList(map);
		int total = cahgWorkTableCategoryService.queryTotal(map);
		
		PageUtils pageUtil = new PageUtils(cahgWorkTableCategoryList, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{workTableCategoryId}")
	@RequiresPermissions("cahgworktablecategory:info")
	public R info(@PathVariable("workTableCategoryId") Integer workTableCategoryId){
		CahgWorkTableCategoryEntity cahgWorkTableCategory = cahgWorkTableCategoryService.queryObject(workTableCategoryId);
		
		return R.ok().put("cahgWorkTableCategory", cahgWorkTableCategory);
	}
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/selectList")
	public R selectList(){
		Map<String, Object> map = new HashMap<>();
		
		//查询列表数据
		List<CahgWorkTableCategoryEntity> list = cahgWorkTableCategoryService.queryAllList();
		
		
		return R.ok().put("list",list);
	}
	
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("cahgworktablecategory:save")
	public R save(@RequestBody CahgWorkTableCategoryEntity cahgWorkTableCategory){
		cahgWorkTableCategory.setCreateUserId(ShiroUtils.getUserId());
		cahgWorkTableCategoryService.save(cahgWorkTableCategory);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("cahgworktablecategory:update")
	public R update(@RequestBody CahgWorkTableCategoryEntity cahgWorkTableCategory){
		cahgWorkTableCategory.setLastUpdateUserId(ShiroUtils.getUserId());
		cahgWorkTableCategoryService.update(cahgWorkTableCategory);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete")
	@RequiresPermissions("cahgworktablecategory:delete")
	public R delete(@RequestBody Integer[] workTableCategoryIds){
		cahgWorkTableCategoryService.deleteBatch(workTableCategoryIds);
		
		return R.ok();
	}
	
}
