package io.renren.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import io.renren.entity.CahgStyleCategoryEntity;
import io.renren.service.CahgStyleCategoryService;
import io.renren.utils.PageUtils;
import io.renren.utils.R;

/**
 * 
 * 
 * @author 
 * @email 
 * @date 2017-06-26 16:54:27
 */
@Controller
@RequestMapping("cahgstylecategory")
public class CahgStyleCategoryController {
	@Autowired
	private CahgStyleCategoryService cahgStyleCategoryService;
	
	@RequestMapping("/cahgstylecategory.html")
	public String list(){
		return "cahgstylecategory/cahgstylecategory.html";
	}
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("cahgstylecategory:list")
	public R list(String title, Integer page, Integer limit) {
		Map<String, Object> map = new HashMap<>();
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		map.put("cateName", title);
		
		// 查询列表数据
		List<CahgStyleCategoryEntity> cahgStyleCategoryList = cahgStyleCategoryService.queryList(map);
		int total = cahgStyleCategoryService.queryTotal(map);

		PageUtils pageUtil = new PageUtils(cahgStyleCategoryList, total, limit, page);

		return R.ok().put("page", pageUtil);
	}

	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{id}")
	@RequiresPermissions("cahgstylecategory:info")
	public R info(@PathVariable("id") Integer id){
		CahgStyleCategoryEntity cahgStyleCategory = cahgStyleCategoryService.queryObject(id);
		return R.ok().put("cahgStyleCategory", cahgStyleCategory);
	}
	
	
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("cahgstylecategory:save")
	public R save(@RequestBody CahgStyleCategoryEntity cahgStyleCategory){
		cahgStyleCategoryService.save(cahgStyleCategory);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("cahgstylecategory:update")
	public R update(@RequestBody CahgStyleCategoryEntity cahgStyleCategory){
		cahgStyleCategoryService.update(cahgStyleCategory);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete")
	@RequiresPermissions("cahgstylecategory:delete")
	public R delete(@RequestBody Integer[] ids){
		cahgStyleCategoryService.deleteBatch(ids);
		return R.ok();
	}
	
	
}
