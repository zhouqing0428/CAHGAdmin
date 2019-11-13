package io.renren.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.stereotype.Controller;

import io.renren.entity.CahgAfficheEntity;
import io.renren.entity.CahgJobEntity;
import io.renren.entity.IpLimitEntity;
import io.renren.entity.PictureEntity;
import io.renren.entity.SysDeptEntity;
import io.renren.service.CahgAfficheService;
import io.renren.service.IpLimitService;
import io.renren.utils.PageUtils;
import io.renren.utils.R;
import io.renren.utils.ShiroUtils;


/**
 * ip限制
 * 
 * @author 
 * @email 
 * @date 2017-07-06 17:50:57
 */
@Controller
@RequestMapping("ipLimit")
public class IpLimitController {
	@Autowired
	private IpLimitService ipLimitService;
	
	@RequestMapping("/ipLimit.html")
	public String list(){
		return "ipLimit/ipLimit.html";
	}
	

	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("ipLimit:list")
	public R list(String status,Integer page, Integer limit){
		Map<String, Object> map = new HashMap<>();
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		map.put("status",status);
		
		//查询列表数据
		List<IpLimitEntity> ipLimitList = ipLimitService.queryList(map);
		int total = ipLimitService.queryTotal(map);
		
		PageUtils pageUtil = new PageUtils(ipLimitList, total, limit, page);
		return R.ok().put("page", pageUtil);
	}
	
	 
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{ipLimitId}")
	public R info(@PathVariable("ipLimitId") Integer ipLimitId){
		IpLimitEntity ipLimitEntity = ipLimitService.queryObject(1);
		return R.ok().put("ipLimitEntity", ipLimitEntity);
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("ipLimit:update")
	public R update(@RequestBody IpLimitEntity ipLimitEntity){
	/*	ipLimitEntity.setLastUpdateUserId(ShiroUtils.getUserId());//最后修改人的id，
		ipLimitEntity.setLastUpdateDate(new Date());*/
		ipLimitService.updateIP(ipLimitEntity);
		
		return R.ok();
	}
	
	
}
