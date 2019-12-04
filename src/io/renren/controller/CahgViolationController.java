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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import io.renren.entity.CahgViolationEntity;
import io.renren.service.CahgViolationService;
import io.renren.utils.PageUtils;
import io.renren.utils.R;
import io.renren.utils.ShiroUtils;

@Controller
@RequestMapping("cahgviolation")
public class CahgViolationController {

	@Autowired
	private CahgViolationService service;
	
	@RequestMapping("/cahgviolation.html")
	public String list(){
		return "cahgviolation/cahgviolation.html";
	}
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("cahgviolation:list")
	public R list(String title, Integer page, Integer limit) {
		Map<String, Object> map = new HashMap<>();
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		map.put("title", title);

		// 查询列表数据
		List<CahgViolationEntity> list = service.queryList(map);
		int total = service.queryTotal(map);

		PageUtils pageUtil = new PageUtils(list, total, limit, page);

		return R.ok().put("page", pageUtil);
	}
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{id}")
	@RequiresPermissions("cahgviolation:info")
	public R info(@PathVariable("id") Integer id){
		CahgViolationEntity info = service.queryObject(id);
		
		return R.ok().put("info", info);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("cahgviolation:save")
	public R save(@RequestBody CahgViolationEntity info){
		info.setCreateUserId(ShiroUtils.getUserEntity().getUserId().toString());
		service.save(info);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("cahgviolation:update")
	public R update(@RequestBody CahgViolationEntity info){
		info.setLastUpdateUserId(ShiroUtils.getUserEntity().getUserId().toString());
		info.setLastUpdateDate(new Date());
		service.update(info);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete")
	@RequiresPermissions("cahgviolation:delete")
	public R delete(@RequestBody Integer[] ids){
		service.deleteBatch(ids);
		
		return R.ok();
	}
	
	/**
	 * 上传  @RequestParam() 必须使用 html name 属性 id不起作用
	 */
	@ResponseBody
	@RequestMapping("/upFile")
	public String upActiImg(@RequestParam("file") CommonsMultipartFile file,
			HttpServletRequest request,HttpServletResponse response) { 
		if (!file.isEmpty()) {
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		    String fileName = sdf.format(date);
			String type = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));// 取文件格式后缀名
			fileName = fileName + type;// 文件名
			String path="E:/file/upImg/cahgViolation/"+fileName;
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
	
	@ResponseBody
	@RequestMapping("/delFile")
	public R delFile(@RequestBody Integer[] specialTopicIds){
		service.updateFileNull(specialTopicIds);
		
		return R.ok();
	}
}
