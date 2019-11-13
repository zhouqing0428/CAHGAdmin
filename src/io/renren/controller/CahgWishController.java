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

import io.renren.entity.CahgWishEntity;
import io.renren.service.CahgWishService;
import io.renren.utils.PageUtils;
import io.renren.utils.R;
import io.renren.utils.ShiroUtils;


/**
 * 
 * 
 * @author 
 * @email 
 * @date 2017-08-07 09:07:10
 */
@Controller
@RequestMapping("cahgwish")
public class CahgWishController {
	@Autowired
	private CahgWishService cahgWishService;
	
	@RequestMapping("/cahgwish.html")
	public String list(){
		return "cahgwish/cahgwish.html";
	}
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("cahgwish:list")
	public R list(Integer page, Integer limit){
		Map<String, Object> map = new HashMap<>();
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		
		//查询列表数据
		List<CahgWishEntity> cahgWishList = cahgWishService.queryList(map);
		int total = cahgWishService.queryTotal(map);
		
		PageUtils pageUtil = new PageUtils(cahgWishList, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{wishId}")
	@RequiresPermissions("cahgwish:info")
	public R info(@PathVariable("wishId") Integer wishId){
		CahgWishEntity cahgWish = cahgWishService.queryObject(wishId);
		
		return R.ok().put("cahgWish", cahgWish);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("cahgwish:save")
	public R save(@RequestBody CahgWishEntity cahgWish){
		cahgWish.setCreateUserId(ShiroUtils.getUserId());
		cahgWishService.save(cahgWish);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("cahgwish:update")
	public R update(@RequestBody CahgWishEntity cahgWish){
		cahgWish.setLastUpdateUserId(ShiroUtils.getUserId());
		cahgWishService.update(cahgWish);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete")
	@RequiresPermissions("cahgwish:delete")
	public R delete(@RequestBody Integer[] wishIds){
		cahgWishService.deleteBatch(wishIds);
		
		return R.ok();
	}
	
}
