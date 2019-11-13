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

import io.renren.entity.CahgOfficeWorkEntity;
import io.renren.service.CahgOfficeWorkService;
import io.renren.utils.PageUtils;
import io.renren.utils.R;
import io.renren.utils.ShiroUtils;


/**
 * 
 * 
 * @author 
 * @email 
 * @date 2017-12-08 17:26:39
 */
@Controller
@RequestMapping("cahgofficework")
public class CahgOfficeWorkController {
	@Autowired
	private CahgOfficeWorkService cahgOfficeWorkService;
	
	@RequestMapping("/cahgofficework.html")
	public String list(){
		return "cahgofficework/cahgofficework.html";
	}
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("cahgofficework:list")
	public R list(String officeWorkCategoryId,Integer page, Integer limit){
		Map<String, Object> map = new HashMap<>();
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		map.put("officeWorkCategoryId", officeWorkCategoryId);
		
		//查询列表数据
		List<CahgOfficeWorkEntity> cahgOfficeWorkList = cahgOfficeWorkService.queryList(map);
		int total = cahgOfficeWorkService.queryTotal(map);
		
		PageUtils pageUtil = new PageUtils(cahgOfficeWorkList, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{officeWorkId}")
	@RequiresPermissions("cahgofficework:info")
	public R info(@PathVariable("officeWorkId") Integer officeWorkId){
		CahgOfficeWorkEntity cahgOfficeWork = cahgOfficeWorkService.queryObject(officeWorkId);
		
		return R.ok().put("cahgOfficeWork", cahgOfficeWork);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("cahgofficework:save")
	public R save(@RequestBody CahgOfficeWorkEntity cahgOfficeWork){
		cahgOfficeWork.setCreateUserId(ShiroUtils.getUserId());
		cahgOfficeWorkService.save(cahgOfficeWork);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("cahgofficework:update")
	public R update(@RequestBody CahgOfficeWorkEntity cahgOfficeWork){
		cahgOfficeWork.setLastUpdateUserId(ShiroUtils.getUserId());
		cahgOfficeWorkService.update(cahgOfficeWork);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete")
	@RequiresPermissions("cahgofficework:delete")
	public R delete(@RequestBody Integer[] officeWorkIds){
		cahgOfficeWorkService.deleteBatch(officeWorkIds);
		
		return R.ok();
	}
	

	/**
	 * 上传文件  @RequestParam() 必须使用 html name 属性 id不起作用
	 */
	@ResponseBody
	@RequestMapping("/upFile") 
	public String upFile(@RequestParam("file") CommonsMultipartFile file,
			HttpServletRequest request,HttpServletResponse response) { // /
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		    String fileName = sdf.format(date);
			String type = file.getOriginalFilename().substring(
					file.getOriginalFilename().lastIndexOf("."));// 取文件格式后缀名
			fileName = fileName + type;// 文件名
			//String path = request.getSession().getServletContext().getRealPath("/upImg/commonforms/" + fileName);// 存放位置
			String path="E:/file/upImg/officeWork/"+fileName;
			File destFile = new File(path);
			try {
				// FileUtils.copyInputStreamToFile()这个方法里对IO进行了自动操作，不需要额外的再去关闭IO流
				FileUtils.copyInputStreamToFile(file.getInputStream(), destFile);// 复制临时文件到指定目录下
				return fileName;
			} catch (IOException e) {
				e.printStackTrace();
			}
		return "err";
	}
	
	
}
