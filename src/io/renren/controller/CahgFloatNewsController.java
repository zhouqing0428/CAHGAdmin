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

import io.renren.entity.CahgDayInfoEntity;
import io.renren.entity.CahgFloatNewsEntity;
import io.renren.entity.SysDeptEntity;
import io.renren.service.CahgFloatNewsService;
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
@RequestMapping("cahgfloatnews")
public class CahgFloatNewsController {
	@Autowired
	private CahgFloatNewsService cahgFloatNewsService;
	@Autowired
	private SysDeptService sysDeptService;
	
	@RequestMapping("/cahgfloatnews.html")
	public String list(){
		return "cahgfloatnews/cahgfloatnews.html";
	}
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("cahgfloatnews:list")
	public R list(String floatNewTitle,String author,Integer page, Integer limit){
		Map<String, Object> map = new HashMap<>();
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		map.put("floatNewTitle", floatNewTitle);
		map.put("author", author);
		map.put("dept_id", ShiroUtils.getDeptId());
		
		//查询列表数据
		List<CahgFloatNewsEntity> cahgFloatNewsList = cahgFloatNewsService.queryList(map);
		int total = cahgFloatNewsService.queryTotal(map);
		
		PageUtils pageUtil = new PageUtils(cahgFloatNewsList, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{floatNewId}")
	@RequiresPermissions("cahgfloatnews:info")
	public R info(@PathVariable("floatNewId") Integer floatNewId){
		CahgFloatNewsEntity cahgFloatNews = cahgFloatNewsService.queryObject(floatNewId);
		return R.ok().put("cahgFloatNews", cahgFloatNews);
	}
	
	/**
	 * 部门列表
	 */
	@ResponseBody
	@RequestMapping("/selectList")
	public R selectList(){
		//查询列表数据
		List<SysDeptEntity> list = sysDeptService.queryDeptList();
		
		return R.ok().put("list", list);
	}
	
	/**
	 * 置顶
	 */
	@ResponseBody
	@RequestMapping("/stick/{floatNewId}")
	@RequiresPermissions("cahgfloatnews:stick")
	public R stick(@PathVariable("floatNewId") Integer floatNewId){
		cahgFloatNewsService.unStick();  
		cahgFloatNewsService.stick(floatNewId);
		return R.ok();
	}
	
	
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("cahgfloatnews:save")
	public R save(@RequestBody CahgFloatNewsEntity cahgFloatNews){
		//cahgFloatNews.setCreateUserId(ShiroUtils.getUserEntity().getUserId());
		cahgFloatNews.setDeptId(ShiroUtils.getUserEntity().getDeptId());

		cahgFloatNewsService.save(cahgFloatNews);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("cahgfloatnews:update")
	public R update(@RequestBody CahgFloatNewsEntity cahgFloatNews){
		cahgFloatNews.setLastUpdateUserId(ShiroUtils.getUserEntity().getUserId());
		cahgFloatNewsService.update(cahgFloatNews);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete")
	@RequiresPermissions("cahgfloatnews:delete")
	public R delete(@RequestBody Integer[] floatNewIds){
		cahgFloatNewsService.deleteBatch(floatNewIds);
		return R.ok();
	}
	
	/**
	 * 上传图片  @RequestParam() 必须使用 html name 属性 id不起作用
	 */
	@ResponseBody
	@RequestMapping("/upFloatUrl")
	public String upActiFloat(@RequestParam("floatUrl") CommonsMultipartFile file,
			HttpServletRequest request,HttpServletResponse response) { // /
		if (!file.isEmpty()) {
			//long startTime = System.currentTimeMillis();
			//System.out.println("-----上传图片开始------");
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		    String fileName = sdf.format(date);
			String type = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));// 取文件格式后缀名
			fileName = fileName + type;// 文件名
			//String path = request.getSession().getServletContext().getRealPath("/upFloat/floatNews/" + fileName);// 存放位置
			String path="D:/file/upFloat/floatNews/"+fileName;
			File destFile = new File(path);
			try {
				// FileUtils.copyInputStreamToFile()这个方法里对IO进行了自动操作，不需要额外的再去关闭IO流
				FileUtils.copyInputStreamToFile(file.getInputStream(), destFile);// 复制临时文件到指定目录下
			//	System.out.println(fileName);
				return fileName;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return "err";
	}
	
	
}
