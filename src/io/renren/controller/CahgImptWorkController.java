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

import io.renren.entity.CahgImptWorkEntity;
import io.renren.service.CahgImptWorkService;
import io.renren.utils.PageUtils;
import io.renren.utils.R;
import io.renren.utils.ShiroUtils;


/**
 * 
 * 
 * @author 
 * @email 
 * @date 2017-07-19 11:17:50
 */
@Controller
@RequestMapping("cahgimptwork")
public class CahgImptWorkController {
	@Autowired
	private CahgImptWorkService cahgImptWorkService;
	
	@RequestMapping("/cahgimptwork.html")
	public String list(){
		return "cahgimptwork/cahgimptwork.html";
	}
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("cahgimptwork:list")
	public R list(Integer page, Integer limit){
		Map<String, Object> map = new HashMap<>();
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		
		//查询列表数据
		List<CahgImptWorkEntity> cahgImptWorkList = cahgImptWorkService.queryList(map);
		int total = cahgImptWorkService.queryTotal(map);
		
		PageUtils pageUtil = new PageUtils(cahgImptWorkList, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{imptWorkId}")
	@RequiresPermissions("cahgimptwork:info")
	public R info(@PathVariable("imptWorkId") Integer imptWorkId){
		CahgImptWorkEntity cahgImptWork = cahgImptWorkService.queryObject(imptWorkId);
		
		return R.ok().put("cahgImptWork", cahgImptWork);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("cahgimptwork:save")
	public R save(@RequestBody CahgImptWorkEntity cahgImptWork){
		cahgImptWork.setCreateUserId(ShiroUtils.getUserId());
		cahgImptWorkService.save(cahgImptWork);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("cahgimptwork:update")
	public R update(@RequestBody CahgImptWorkEntity cahgImptWork){
		cahgImptWork.setLastUpdateUserId(ShiroUtils.getUserId());
		cahgImptWorkService.update(cahgImptWork);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete")
	@RequiresPermissions("cahgimptwork:delete")
	public R delete(@RequestBody Integer[] imptWorkIds){
		cahgImptWorkService.deleteBatch(imptWorkIds);
		
		return R.ok();
	}
	
}
