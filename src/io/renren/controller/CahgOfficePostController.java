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

import io.renren.entity.CahgOfficePostEntity;
import io.renren.service.CahgOfficePostService;
import io.renren.utils.PageUtils;
import io.renren.utils.R;
import io.renren.utils.ShiroUtils;


/**
 * 本办发文
 * 
 * @author 
 * @email 
 * @date 2017-07-17 16:56:20
 */
@Controller
@RequestMapping("cahgofficepost")
public class CahgOfficePostController {
	@Autowired
	private CahgOfficePostService cahgOfficePostService;
	
	@RequestMapping("/cahgofficepost.html")
	public String list(){
		return "cahgofficepost/cahgofficepost.html";
	}
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("cahgofficepost:list")
	public R list(Integer page, Integer limit){
		Map<String, Object> map = new HashMap<>();
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		map.put("dept_id", ShiroUtils.getDeptId());
		
		//查询列表数据
		List<CahgOfficePostEntity> cahgOfficePostList = cahgOfficePostService.queryList(map);
		int total = cahgOfficePostService.queryTotal(map);
		
		PageUtils pageUtil = new PageUtils(cahgOfficePostList, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{officePostId}")
	@RequiresPermissions("cahgofficepost:info")
	public R info(@PathVariable("officePostId") Integer officePostId){
		CahgOfficePostEntity cahgOfficePost = cahgOfficePostService.queryObject(officePostId);
		
		return R.ok().put("cahgOfficePost", cahgOfficePost);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("cahgofficepost:save")
	public R save(@RequestBody CahgOfficePostEntity cahgOfficePost){
		cahgOfficePost.setCreateUserId(ShiroUtils.getUserId());
		cahgOfficePost.setDeptId(ShiroUtils.getUserEntity().getDeptId());
		cahgOfficePostService.save(cahgOfficePost);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("cahgofficepost:update")
	public R update(@RequestBody CahgOfficePostEntity cahgOfficePost){
		cahgOfficePost.setLastUpdateUserId(ShiroUtils.getUserId());
		//
		cahgOfficePostService.update(cahgOfficePost);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete")
	@RequiresPermissions("cahgofficepost:delete")
	public R delete(@RequestBody Integer[] officePostIds){
		cahgOfficePostService.deleteBatch(officePostIds);
		
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
			//String path = request.getSession().getServletContext().getRealPath("/upImg/officePost/" + fileName);// 存放位置
			String path="E:/file/upImg/officePost/"+fileName;
			File destFile = new File(path);
			//System.out.println(path);
			try {
				// FileUtils.copyInputStreamToFile()这个方法里对IO进行了自动操作，不需要额外的再去关闭IO流
				FileUtils.copyInputStreamToFile(file.getInputStream(), destFile);// 复制临时文件到指定目录下
				System.out.println(fileName);
				return fileName;
			} catch (IOException e) {
				e.printStackTrace();
			}
		return "err";
	}
	
}
