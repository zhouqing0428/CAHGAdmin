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

import io.renren.entity.CahgIntroFileEntity;
import io.renren.service.CahgIntroFileService;
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
@RequestMapping("cahgintrofile")
public class CahgIntroFileController {
	@Autowired
	private CahgIntroFileService cahgIntroFileService;
	
	@RequestMapping("/cahgintrofile.html")
	public String list(){
		return "cahgintrofile/cahgintrofile.html";
	}
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("cahgintrofile:list")
	public R list(Integer page, Integer limit){
		Map<String, Object> map = new HashMap<>();
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		
		//查询列表数据
		List<CahgIntroFileEntity> cahgIntroFileList = cahgIntroFileService.queryList(map);
		int total = cahgIntroFileService.queryTotal(map);
		
		PageUtils pageUtil = new PageUtils(cahgIntroFileList, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{introFileId}")
	@RequiresPermissions("cahgintrofile:info")
	public R info(@PathVariable("introFileId") Integer introFileId){
		CahgIntroFileEntity cahgIntroFile = cahgIntroFileService.queryObject(introFileId);
		
		return R.ok().put("cahgIntroFile", cahgIntroFile);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("cahgintrofile:save")
	public R save(@RequestBody CahgIntroFileEntity cahgIntroFile){
		cahgIntroFile.setCreateUserId(ShiroUtils.getUserId());
		cahgIntroFileService.save(cahgIntroFile);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("cahgintrofile:update")
	public R update(@RequestBody CahgIntroFileEntity cahgIntroFile){
		cahgIntroFile.setLastUpdateUserId(ShiroUtils.getUserId());
		cahgIntroFileService.update(cahgIntroFile);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete")
	@RequiresPermissions("cahgintrofile:delete")
	public R delete(@RequestBody Integer[] introFileIds){
		cahgIntroFileService.deleteBatch(introFileIds);
		
		return R.ok();
	}
	
	/**
	 * 上传文件  @RequestParam() 必须使用 html name 属性 id不起作用
	 */
	@ResponseBody
	@RequestMapping("/upFile")
	public String upFile(@RequestParam("file") CommonsMultipartFile file,
			HttpServletRequest request,HttpServletResponse response) { // /
			//long startTime = System.currentTimeMillis();
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		    String fileName = sdf.format(date);
			String type = file.getOriginalFilename().substring(
					file.getOriginalFilename().lastIndexOf("."));// 取文件格式后缀名
			fileName = fileName + type;// 文件名
		//	String path = request.getSession().getServletContext().getRealPath("/upImg/workTable/" + fileName);// 存放位置
			String path="E:/file/upImg/introfile/"+fileName;
			File destFile = new File(path);
			//System.out.println(path);
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
