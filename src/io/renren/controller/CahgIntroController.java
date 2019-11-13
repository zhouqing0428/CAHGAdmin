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

import io.renren.entity.CahgIntroEntity;
import io.renren.service.CahgIntroService;
import io.renren.utils.PageUtils;
import io.renren.utils.R;
import io.renren.utils.ShiroUtils;


/**
 * 
 * 
 * @author 
 * @email 
 * @date 2018-03-20 13:45:52
 */
@Controller
@RequestMapping("cahgintro")
public class CahgIntroController {
	@Autowired
	private CahgIntroService cahgIntroService;
	
	@RequestMapping("/cahgintro.html")
	public String list(){
		return "cahgintro/cahgintro.html";
	}
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("cahgintro:list")
	public R list(Integer page, Integer limit){
		Map<String, Object> map = new HashMap<>();
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		
		//查询列表数据
		List<CahgIntroEntity> cahgIntroList = cahgIntroService.queryList(map);
		int total = cahgIntroService.queryTotal(map);
		
		PageUtils pageUtil = new PageUtils(cahgIntroList, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{introId}")
	@RequiresPermissions("cahgintro:info")
	public R info(@PathVariable("introId") Integer introId){
		CahgIntroEntity cahgIntro = cahgIntroService.queryObject(introId);
		
		return R.ok().put("cahgIntro", cahgIntro);
	}
	
	/**
	 * 保存
	 */
	/*@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("cahgintro:save")
	public R save(@RequestBody CahgIntroEntity cahgIntro){
		cahgIntroService.save(cahgIntro);
		
		return R.ok();
	}*/
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
//	@RequiresPermissions("cahgintro:update")
	public R update(@RequestBody CahgIntroEntity cahgIntro){
		cahgIntro.setLastUpdateUserId(ShiroUtils.getUserId());
		cahgIntroService.update(cahgIntro);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	/*@ResponseBody
	@RequestMapping("/delete")
	@RequiresPermissions("cahgintro:delete")
	public R delete(@RequestBody Integer[] introIds){
		cahgIntroService.deleteBatch(introIds);
		
		return R.ok();
	}*/
	
}
