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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.stereotype.Controller;

import io.renren.entity.CahgSpecialTopicEntity;
import io.renren.service.CahgSpecialTopicService;
import io.renren.utils.PageUtils;
import io.renren.utils.R;
import io.renren.utils.ShiroUtils;


/**
 * 专题内容
 * 
 * @author 
 * @email 
 * @date 2017-07-20 10:13:31
 */
@Controller
@RequestMapping("cahgspecialtopic")
public class CahgSpecialTopicController {
	@Autowired
	private CahgSpecialTopicService cahgSpecialTopicService;
	
	@RequestMapping("/cahgspecialtopic.html")
	public String list(){
		return "cahgspecialtopic/cahgspecialtopic.html";
	}
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("cahgspecialtopic:list")
	public R list(String title,String specialTopicCategoryId,Integer page, Integer limit){
		Map<String, Object> map = new HashMap<>();
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		map.put("title", title);
		map.put("specialTopicCategoryId", specialTopicCategoryId);
		map.put("dept_id", ShiroUtils.getDeptId());
		
		//查询列表数据
		List<CahgSpecialTopicEntity> cahgSpecialTopicList = cahgSpecialTopicService.queryList(map);
		int total = cahgSpecialTopicService.queryTotal(map);
		
		PageUtils pageUtil = new PageUtils(cahgSpecialTopicList, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{specialTopicId}")
	@RequiresPermissions("cahgspecialtopic:info")
	public R info(@PathVariable("specialTopicId") Integer specialTopicId){
		CahgSpecialTopicEntity cahgSpecialTopic = cahgSpecialTopicService.queryObject(specialTopicId);
		
		return R.ok().put("cahgSpecialTopic", cahgSpecialTopic);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("cahgspecialtopic:save")
	public R save(@RequestBody CahgSpecialTopicEntity cahgSpecialTopic){
		cahgSpecialTopic.setCreateUserId(ShiroUtils.getUserId());
		cahgSpecialTopic.setDeptId(ShiroUtils.getUserEntity().getDeptId());

		cahgSpecialTopicService.save(cahgSpecialTopic);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("cahgspecialtopic:update")
	public R update(@RequestBody CahgSpecialTopicEntity cahgSpecialTopic){
		cahgSpecialTopic.setLastUpdateUserId(ShiroUtils.getUserId());
		cahgSpecialTopicService.update(cahgSpecialTopic);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete")
	@RequiresPermissions("cahgspecialtopic:delete")
	public R delete(@RequestBody Integer[] specialTopicIds){
		cahgSpecialTopicService.deleteBatch(specialTopicIds);
		
		return R.ok();
	}
	
	//
	@ResponseBody
	@RequestMapping("/delFile")
	public R delFile(@RequestBody Integer[] specialTopicIds){
		cahgSpecialTopicService.updateFileNull(specialTopicIds);
		
		return R.ok();
	}
	
	
	/**
	 * 上传  @RequestParam() 必须使用 html name 属性 id不起作用
	 */
	@ResponseBody
	@RequestMapping("/upFile")
	public String upActiImg(@RequestParam("file") CommonsMultipartFile file,
			HttpServletRequest request,HttpServletResponse response) { // /
		if (!file.isEmpty()) {
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		    String fileName = sdf.format(date);
			String type = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));// 取文件格式后缀名
			fileName = fileName + type;// 文件名
			String path="E:/file/upImg/specialtopic/"+fileName;
			File destFile = new File(path);
			try {
				FileUtils.copyInputStreamToFile(file.getInputStream(), destFile);// 复制临时文件到指定目录下
				return fileName;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return "err";
	}
	
}
