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

import io.renren.entity.CahgImgNewsEntity;
import io.renren.entity.SysDeptEntity;
import io.renren.service.CahgImgNewsService;
import io.renren.service.SysDeptService;
import io.renren.utils.PageUtils;
import io.renren.utils.R;
import io.renren.utils.ShiroUtils;

/**
 * 图片新闻
 * 
 * @author 
 * @email 
 * @date 2017-06-26 16:54:27
 */
@Controller
@RequestMapping("cahgimgnews")
public class CahgImgNewsController {
	@Autowired
	private CahgImgNewsService cahgImgNewsService;
	@Autowired
	private SysDeptService sysDeptService;
	
	@RequestMapping("/cahgimgnews.html")
	public String list(){
		return "cahgimgnews/cahgimgnews.html";
	}
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("cahgimgnews:list")
	public R list(String imgNewTitle, String author, Integer page, Integer limit) {
		Map<String, Object> map = new HashMap<>();
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		map.put("imgNewTitle", imgNewTitle);
		map.put("author", author);
		map.put("dept_id", ShiroUtils.getDeptId());
		
		//查询列表数据
		List<CahgImgNewsEntity> cahgImgNewsList = cahgImgNewsService.queryList(map);
		int total = cahgImgNewsService.queryTotal(map);
		
		PageUtils pageUtil = new PageUtils(cahgImgNewsList, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{imgNewId}")
	@RequiresPermissions("cahgimgnews:info")
	public R info(@PathVariable("imgNewId") Integer imgNewId){
		CahgImgNewsEntity cahgImgNews = cahgImgNewsService.queryObject(imgNewId);
		return R.ok().put("cahgImgNews", cahgImgNews);
	}
	
	/**
	 * 部门列表
	 */
	@ResponseBody
	@RequestMapping("/selectList")
	public R selectList(){
		//查询列表数据
		Map<String, Object> map = new HashMap<>();
		map.put("condition", " dept_id != 38 ");
		List<SysDeptEntity> list = sysDeptService.queryDeptList(map);
		
		return R.ok().put("list", list);
	}
	
	/**
	 * 置顶
	 */
	@ResponseBody
	@RequestMapping("/stick/{imgNewId}")
	@RequiresPermissions("cahgimgnews:stick")
	public R stick(@PathVariable("imgNewId") Integer imgNewId){
		cahgImgNewsService.unStick();  
		cahgImgNewsService.stick(imgNewId);
		return R.ok();
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("cahgimgnews:save")
	public R save(@RequestBody CahgImgNewsEntity cahgImgNews){

		cahgImgNewsService.save(cahgImgNews);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("cahgimgnews:update")
	public R update(@RequestBody CahgImgNewsEntity cahgImgNews){
		cahgImgNews.setLastUpdateUserId(ShiroUtils.getUserEntity().getUserId());
		cahgImgNewsService.update(cahgImgNews);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete")
	@RequiresPermissions("cahgimgnews:delete")
	public R delete(@RequestBody Integer[] imgNewIds){
		cahgImgNewsService.deleteBatch(imgNewIds);
		return R.ok();
	}
	
	/**
	 * 上传图片  @RequestParam() 必须使用 html name 属性 id不起作用
	 */
	@ResponseBody
	@RequestMapping("/upImgUrl")
	public String upActiImg(@RequestParam("imgUrl") CommonsMultipartFile file,
			HttpServletRequest request,HttpServletResponse response) { // /
		if (!file.isEmpty()) {
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		    String fileName = sdf.format(date);
			String type = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));// 取文件格式后缀名
			fileName = fileName + type;// 文件名
			String path="E:/file/upImg/imgNews/"+fileName;
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
