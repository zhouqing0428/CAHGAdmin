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

import io.renren.entity.CahgCustomNewsEntity;
import io.renren.entity.SysDeptEntity;
import io.renren.service.CahgCustomNewsService;
import io.renren.utils.PageUtils;
import io.renren.utils.R;
import io.renren.utils.ShiroUtils;


/**
 * 海关新闻
 * 
 * @author 
 * @email 
 * @date 2017-07-04 10:15:43
 */
@Controller
@RequestMapping("cahgcustomnews")
public class CahgCustomNewsController {
	@Autowired
	private CahgCustomNewsService cahgCustomNewsService;
	
	@RequestMapping("/cahgcustomnews.html")
	public String list(){
		return "cahgcustomnews/cahgcustomnews.html";
	}
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("cahgcustomnews:list")
	public R list(String title,String author,Integer page, Integer limit){
		Map<String, Object> map = new HashMap<>();
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		map.put("title", title);
		map.put("author", author);
		map.put("dept_id", ShiroUtils.getDeptId());
		
		//查询列表数据
		List<CahgCustomNewsEntity> cahgCustomNewsList = cahgCustomNewsService.queryList(map);
		int total = cahgCustomNewsService.queryTotal(map);
		
		PageUtils pageUtil = new PageUtils(cahgCustomNewsList, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{customNewsId}")
	@RequiresPermissions("cahgcustomnews:info")
	public R info(@PathVariable("customNewsId") Integer customNewsId){
		CahgCustomNewsEntity cahgCustomNews = cahgCustomNewsService.queryObject(customNewsId);
		
		return R.ok().put("cahgCustomNews", cahgCustomNews);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("cahgcustomnews:save")
	public R save(@RequestBody CahgCustomNewsEntity cahgCustomNews){
		cahgCustomNews.setCreateUserId(ShiroUtils.getUserId());
		cahgCustomNews.setDeptId(ShiroUtils.getUserEntity().getDeptId());
		cahgCustomNewsService.save(cahgCustomNews);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("cahgcustomnews:update")
	public R update(@RequestBody CahgCustomNewsEntity cahgCustomNews){
		cahgCustomNews.setLastUpdateUserId(ShiroUtils.getUserId());
		cahgCustomNewsService.update(cahgCustomNews);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete")
	@RequiresPermissions("cahgcustomnews:delete")
	public R delete(@RequestBody Integer[] customNewsIds){
		cahgCustomNewsService.deleteBatch(customNewsIds);
		
		return R.ok();
	}

	//
	@ResponseBody
	@RequestMapping("/delFile")
	public R delFile(@RequestBody Integer[] customNewsIds){
		cahgCustomNewsService.updateFileNull(customNewsIds);
		
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
			String path="E:/file/upImg/cahgCustomNews/"+fileName;
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

	//上传文件
	@RequestMapping(value="/upload",method=RequestMethod.POST)
	@ResponseBody
	public R upload(@RequestParam(value="file") MultipartFile  file, @RequestParam(value="customNewsId") String customNewsId, 
			@RequestParam(value="titles") String titles,HttpServletRequest request) throws IOException{
		titles=java.net.URLDecoder.decode(titles,"utf-8");
		String originalFilename = file.getOriginalFilename();
		String suffixFileName = originalFilename.substring(originalFilename.indexOf(".") + 1);
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
		String datePath = sdf.format(date);
	    String dateFileName = datePath + "." + suffixFileName;
		String path = request.getSession().getServletContext().getRealPath("/upImg/cahgCustomNews/");
		String filePath = path + "\\" + dateFileName;
		file.transferTo(new File(filePath));
		CahgCustomNewsEntity cahgCustomNewsEntity = null;
		if(customNewsId != null && !"".equals(customNewsId)){//文件上传更新
			cahgCustomNewsEntity = cahgCustomNewsService.queryObject(Integer.valueOf(customNewsId));
			cahgCustomNewsEntity.setTitle(titles);
			cahgCustomNewsEntity.setCustomFilePath(dateFileName);
			cahgCustomNewsEntity.setCustomFileName(originalFilename);
			//设置修改人
			cahgCustomNewsEntity.setLastUpdateUserId(ShiroUtils.getUserId());//最后修改人的id，
			cahgCustomNewsService.update(cahgCustomNewsEntity);
		}else{//文件上传新增
			cahgCustomNewsEntity = new CahgCustomNewsEntity();
			cahgCustomNewsEntity.setTitle(titles);
			cahgCustomNewsEntity.setCustomFilePath(dateFileName);
			cahgCustomNewsEntity.setCustomFileName(originalFilename);
			//设置新增人
			cahgCustomNewsEntity.setCreateUserId(ShiroUtils.getUserId());//新增的人id，时间
			cahgCustomNewsEntity.setDeptId(ShiroUtils.getUserEntity().getDeptId());
			cahgCustomNewsService.save(cahgCustomNewsEntity);
		}
		R r = R.ok();
		r.put("customFileName",originalFilename);
		r.put("customFilePath",dateFileName);
		r.put("customNewsId", cahgCustomNewsEntity.getCustomNewsId());
		r.put("titles",titles);
	    return r;
	}
	
}
