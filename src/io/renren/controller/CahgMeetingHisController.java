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

import io.renren.entity.CahgMeetingHisEntity;
import io.renren.service.CahgMeetingHisService;
import io.renren.utils.PageUtils;
import io.renren.utils.R;
import io.renren.utils.ShiroUtils;

/**
 * 
 * 
 * @author
 * @email
 * @date 2018-03-23 15:13:55
 */
@Controller
@RequestMapping("cahgmeetinghis")
public class CahgMeetingHisController {
	@Autowired
	private CahgMeetingHisService cahgMeetingHisService;

	@RequestMapping("/cahgmeetinghis.html")
	public String list() {
		return "cahgmeetinghis/cahgmeetinghis.html";
	}

	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("cahgmeetinghis:list")
	public R list(Integer page, Integer limit) {
		Map<String, Object> map = new HashMap<>();
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);

		// 查询列表数据
		List<CahgMeetingHisEntity> cahgMeetingHisList = cahgMeetingHisService
				.queryList(map);
		int total = cahgMeetingHisService.queryTotal(map);

		PageUtils pageUtil = new PageUtils(cahgMeetingHisList, total, limit,
				page);

		return R.ok().put("page", pageUtil);
	}

	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{meetingHisId}")
	@RequiresPermissions("cahgmeetinghis:info")
	public R info(@PathVariable("meetingHisId") Integer meetingHisId) {
		CahgMeetingHisEntity cahgMeetingHis = cahgMeetingHisService
				.queryObject(meetingHisId);

		return R.ok().put("cahgMeetingHis", cahgMeetingHis);
	}

	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("cahgmeetinghis:save")
	public R save(@RequestBody CahgMeetingHisEntity cahgMeetingHis) {
		cahgMeetingHis.setCreateUserId(ShiroUtils.getUserId());

		if (null==cahgMeetingHis.getMeetingRoomId() ) {
			cahgMeetingHisService.save(cahgMeetingHis);
			return R.ok();
		} else {
			int total=cahgMeetingHisService.queryMeetingApplied(cahgMeetingHis);
			System.out.println("total="+total);
			if(total>=1){
				return R.error("该会议室这时间段内已被申请，请修改");
			}
			cahgMeetingHisService.save(cahgMeetingHis);
			return R.ok();
		}

	}

	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("cahgmeetinghis:update")
	public R update(@RequestBody CahgMeetingHisEntity cahgMeetingHis) {
		cahgMeetingHis.setLastUpdateUserId(ShiroUtils.getUserId());

		if (null==cahgMeetingHis.getMeetingRoomId()) {
			cahgMeetingHisService.update(cahgMeetingHis);
			return R.ok();
		} else {
			int total=cahgMeetingHisService.queryMeetingApplied(cahgMeetingHis);
			System.out.println("total="+total);
			if(total>=1){
				return R.error("该会议室这时间段内已被申请，请修改");
			}
			cahgMeetingHisService.update(cahgMeetingHis);
			return R.ok();
		}

	}

	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete")
	@RequiresPermissions("cahgmeetinghis:delete")
	public R delete(@RequestBody Integer[] meetingHisIds) {
		cahgMeetingHisService.deleteBatch(meetingHisIds);

		return R.ok();
	}

}
