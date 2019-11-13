package io.renren.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.javassist.expr.NewArray;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.stereotype.Controller;

import io.renren.entity.CahgMeetingRoomEntity;
import io.renren.service.CahgMeetingRoomService;
import io.renren.utils.PageUtils;
import io.renren.utils.R;
import io.renren.utils.ShiroUtils;


/**
 * 
 * 
 * @author 
 * @email 
 * @date 2017-10-12 16:03:57
 */
@Controller
@RequestMapping("cahgmeetingroom")
public class CahgMeetingRoomController {
	@Autowired
	private CahgMeetingRoomService cahgMeetingRoomService;
	
	@RequestMapping("/cahgmeetingroom.html")
	public String list(){
		return "cahgmeetingroom/cahgmeetingroom.html";
	}
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("cahgmeetingroom:list")
	public R list(Integer page, Integer limit){
		Map<String, Object> map = new HashMap<>();
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		
		//查询列表数据
		List<CahgMeetingRoomEntity> cahgMeetingRoomList = cahgMeetingRoomService.queryList(map);
		int total = cahgMeetingRoomService.queryTotal(map);
		
		PageUtils pageUtil = new PageUtils(cahgMeetingRoomList, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{meetingRoomId}")
	@RequiresPermissions("cahgmeetingroom:info")
	public R info(@PathVariable("meetingRoomId") Integer meetingRoomId){
		CahgMeetingRoomEntity cahgMeetingRoom = cahgMeetingRoomService.queryObject(meetingRoomId);
		
		return R.ok().put("cahgMeetingRoom", cahgMeetingRoom);
	}
	
	
	@ResponseBody
	@RequestMapping("/selectList")
	public R selectList(){
		Map<String, Object> map = new HashMap<>();
		List<CahgMeetingRoomEntity> list = cahgMeetingRoomService.queryList(map);
		return R.ok().put("list", list);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("cahgmeetingroom:save")
	public R save(@RequestBody CahgMeetingRoomEntity cahgMeetingRoom){
		cahgMeetingRoom.setCreateUserId(ShiroUtils.getUserId());
		cahgMeetingRoomService.save(cahgMeetingRoom);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("cahgmeetingroom:update")
	public R update(@RequestBody CahgMeetingRoomEntity cahgMeetingRoom){
		cahgMeetingRoom.setLastUpdateUserId(ShiroUtils.getUserId());
		cahgMeetingRoomService.update(cahgMeetingRoom);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete")
	@RequiresPermissions("cahgmeetingroom:delete")
	public R delete(@RequestBody Integer[] meetingRoomIds){
		cahgMeetingRoomService.deleteBatch(meetingRoomIds);
		
		return R.ok();
	}
	
	/**
	 * 可用状态
	 */
	@ResponseBody
	@RequestMapping("/usable")
	@RequiresPermissions("cahgmeetingroom:usable")
	public R usable(@RequestBody Integer[] meetingRoomIds){
		cahgMeetingRoomService.updateUsable(meetingRoomIds);
		
		return R.ok();
	}
	/**
	 * 不可用,占用状态
	 */
	@ResponseBody
	@RequestMapping("/unusable")
	@RequiresPermissions("cahgmeetingroom:unusable")
	public R unusable(@RequestBody Integer[] meetingRoomIds){
		cahgMeetingRoomService.updateUnusable(meetingRoomIds);
		
		return R.ok();
	}
}
