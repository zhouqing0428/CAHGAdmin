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

import io.renren.entity.CahgLetterEntity;
import io.renren.entity.CahgReplyLetterEntity;
import io.renren.entity.SysUserEntity;
import io.renren.service.CahgLetterService;
import io.renren.service.SysUserService;
import io.renren.utils.PageUtils;
import io.renren.utils.R;
import io.renren.utils.ShiroUtils;


/**
 * 
 * 
 * @author 
 * @email 
 * @date 2017-08-04 15:31:44
 */
@Controller
@RequestMapping("cahgletter")
public class CahgLetterController {
	@Autowired
	private CahgLetterService cahgLetterService;
	@Autowired
	private SysUserService sysUserService;
	
	@RequestMapping("/cahgletter.html")
	public String list(){
		return "cahgletter/cahgletter.html";
	}
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("cahgletter:list")
	public R list(Integer page, Integer limit){
		Map<String, Object> map = new HashMap<>();
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		map.put("userId", ShiroUtils.getUserId());
		
		//查询列表数据
		List<CahgLetterEntity> cahgLetterList = cahgLetterService.queryList(map);
		int total = cahgLetterService.queryTotal(map);
		
		PageUtils pageUtil = new PageUtils(cahgLetterList, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	
	/**
	 * 未读信封数量
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/queryUnReadTotal")
	public R queryUnReadTotal(){
		if(ShiroUtils.getUserEntity().getIsLetterLeader()==1){ //信箱领导
			Map<String, Object> map =new HashMap<>();
			map.put("userId", ShiroUtils.getUserId());
			int count = cahgLetterService.queryUnReadTotal(map);
			return R.ok().put("count", count);
		}
		return R.ok().put("count", "不是信箱领导");
	}
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{letterId}")
	@RequiresPermissions("cahgletter:info")
	public R info(@PathVariable("letterId") Integer letterId){
		CahgLetterEntity cahgLetter = cahgLetterService.queryObject(letterId);
		
		return R.ok().put("cahgLetter", cahgLetter);
	}
	
	/**
	 * 保存回信信息
	 */
	@ResponseBody
	@RequestMapping("/saveReplyLetter")
	public R saveReplyLetter(@RequestBody CahgReplyLetterEntity cahgReplyLetter){
		cahgReplyLetter.setCreateUserId(ShiroUtils.getUserId());
		cahgLetterService.saveReplyLetter(cahgReplyLetter);
		Map<String, Object> map=new HashMap<>();
		map.put("letterId", cahgReplyLetter.getLetterId());
		map.put("isReply", 1);
		cahgLetterService.updateReplyStatus(map);
		
		return R.ok();
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("cahgletter:save")
	public R save(@RequestBody CahgLetterEntity cahgLetter){
		cahgLetterService.save(cahgLetter);
		
		return R.ok();
	}
	
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("cahgletter:update")
	public R update(@RequestBody CahgLetterEntity cahgLetter){
		cahgLetterService.update(cahgLetter);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete")
//	@RequiresPermissions("cahgletter:delete")
	public R delete(@RequestBody Integer[] letterIds){
		SysUserEntity user = sysUserService.queryObject(ShiroUtils.getUserId());
		if(1==user.getIsLetterLeader()){
			//cahgLetterService.deleteBatch(letterIds); 真删除
			cahgLetterService.updateIsDelete(letterIds);  //伪删除
			cahgLetterService.deleteReplyBatch(letterIds);  //删除回信信息
			return R.ok();
		}else {
			return R.error("信箱主任才能删除");
		}
	}
	
	/*
	 * 
	 * 设置为已读
	 */
	@ResponseBody
	@RequestMapping("/hadRead")
	public R hadRead(@RequestBody Integer[] letterIds){
		cahgLetterService.hadRead(letterIds);
		
		return R.ok();
	}
	
	@ResponseBody
	@RequestMapping("/show")
	public R show(@RequestBody Integer[] letterIds){
		cahgLetterService.show(letterIds);
		
		return R.ok();
	}
	
	@ResponseBody
	@RequestMapping("/hide")
	public R hide(@RequestBody Integer[] letterIds){
		cahgLetterService.hide(letterIds);
		
		return R.ok();
	}
}
