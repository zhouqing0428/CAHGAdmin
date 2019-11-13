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

import io.renren.entity.CahgHyperlinkEntity;
import io.renren.service.CahgHyperlinkService;
import io.renren.utils.PageUtils;
import io.renren.utils.R;
import io.renren.utils.ShiroUtils;


/**
 * 
 * 
 * @author 
 * @email 
 * @date 2017-07-25 11:37:11
 */
@Controller
@RequestMapping("cahghyperlink")
public class CahgHyperlinkController {
	@Autowired
	private CahgHyperlinkService cahgHyperlinkService;
	
	@RequestMapping("/cahghyperlink.html")
	public String list(){
		return "cahghyperlink/cahghyperlink.html";
	}
	
	/**
	 * 常规链接列表
	 */
	@ResponseBody
	@RequestMapping("/headList")
	@RequiresPermissions("cahghyperlink:headList")
	public R headList(Integer page, Integer limit){
		Map<String, Object> map = new HashMap<>();
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		
		//查询列表数据
		List<CahgHyperlinkEntity> cahgHyperlinkList = cahgHyperlinkService.headList(map);
		int total = cahgHyperlinkService.queryTotal(map);
		
		PageUtils pageUtil = new PageUtils(cahgHyperlinkList, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	
	/**
	 * 底部链接列表
	 */
	@ResponseBody
	@RequestMapping("/rootList")
	@RequiresPermissions("cahghyperlink:rootList")
	public R rootList(Integer page, Integer limit){
		Map<String, Object> map = new HashMap<>();
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		
		//查询列表数据
		List<CahgHyperlinkEntity> cahgHyperlinkList = cahgHyperlinkService.rootList(map);
		int total = cahgHyperlinkService.queryTotal(map);
		
		PageUtils pageUtil = new PageUtils(cahgHyperlinkList, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{linkId}")
	@RequiresPermissions("cahghyperlink:info")
	public R info(@PathVariable("linkId") Integer linkId){
		CahgHyperlinkEntity cahgHyperlink = cahgHyperlinkService.queryObject(linkId);
		
		return R.ok().put("cahgHyperlink", cahgHyperlink);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("cahghyperlink:save")
	public R save(@RequestBody CahgHyperlinkEntity cahgHyperlink){
		cahgHyperlink.setCreateUserId(ShiroUtils.getUserId());
		cahgHyperlinkService.save(cahgHyperlink);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("cahghyperlink:update")
	public R update(@RequestBody CahgHyperlinkEntity cahgHyperlink){
		cahgHyperlink.setLastUpdateUserId(ShiroUtils.getUserId());
		cahgHyperlinkService.update(cahgHyperlink);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete")
	@RequiresPermissions("cahghyperlink:delete")
	public R delete(@RequestBody Integer[] linkIds){
		cahgHyperlinkService.deleteBatch(linkIds);
		
		return R.ok();
	}
	
}
