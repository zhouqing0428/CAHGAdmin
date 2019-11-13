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

import io.renren.entity.CahgSpecialTopicCategoryEntity;
import io.renren.entity.SysDeptEntity;
import io.renren.service.CahgSpecialTopicCategoryService;
import io.renren.utils.PageUtils;
import io.renren.utils.R;
import io.renren.utils.ShiroUtils;


/**
 * 专题类别
 * 
 * @author 
 * @email 
 * @date 2017-07-19 17:27:39
 */
@Controller
@RequestMapping("cahgspecialtopiccategory")
public class CahgSpecialTopicCategoryController {
	@Autowired
	private CahgSpecialTopicCategoryService cahgSpecialTopicCategoryService;
	
	@RequestMapping("/cahgspecialtopiccategory.html")
	public String list(){
		return "cahgspecialtopiccategory/cahgspecialtopiccategory.html";
	}
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("cahgspecialtopiccategory:list")
	public R list(Integer page, Integer limit){
		Map<String, Object> map = new HashMap<>();
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		
		//查询列表数据
		List<CahgSpecialTopicCategoryEntity> cahgSpecialTopicCategoryList = cahgSpecialTopicCategoryService.queryList(map);
		int total = cahgSpecialTopicCategoryService.queryTotal(map);
		
		PageUtils pageUtil = new PageUtils(cahgSpecialTopicCategoryList, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{specialTopicCategoryId}")
	@RequiresPermissions("cahgspecialtopiccategory:info")
	public R info(@PathVariable("specialTopicCategoryId") Integer specialTopicCategoryId){
		CahgSpecialTopicCategoryEntity cahgSpecialTopicCategory = cahgSpecialTopicCategoryService.queryObject(specialTopicCategoryId);
		
		return R.ok().put("cahgSpecialTopicCategory", cahgSpecialTopicCategory);
	}
	
	/**
	 * 所有专题类别
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/selectList")
	public R selectList(){
		//查询列表数据
		Map<String, Object> map = new HashMap<>();
		List<CahgSpecialTopicCategoryEntity> list = cahgSpecialTopicCategoryService.queryList(map);
		
		return R.ok().put("list", list);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("cahgspecialtopiccategory:save")
	public R save(@RequestBody CahgSpecialTopicCategoryEntity cahgSpecialTopicCategory){
		cahgSpecialTopicCategory.setCreateUserId(ShiroUtils.getUserId());
		cahgSpecialTopicCategoryService.save(cahgSpecialTopicCategory);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("cahgspecialtopiccategory:update")
	public R update(@RequestBody CahgSpecialTopicCategoryEntity cahgSpecialTopicCategory){
		cahgSpecialTopicCategory.setLastUpdateUserId(ShiroUtils.getUserId());
		cahgSpecialTopicCategoryService.update(cahgSpecialTopicCategory);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete")
	@RequiresPermissions("cahgspecialtopiccategory:delete")
	public R delete(@RequestBody Integer[] specialTopicCategoryIds){
		cahgSpecialTopicCategoryService.deleteBatch(specialTopicCategoryIds);
		
		return R.ok();
	}
	
	/**
	 * 上传图片  @RequestParam() 必须使用 html name 属性 id不起作用
	 */
	@ResponseBody
	@RequestMapping("/upImg")
	public String upImg(@RequestParam("img") CommonsMultipartFile file,
			HttpServletRequest request,HttpServletResponse response) { // /
		if (!file.isEmpty()) {
			//long startTime = System.currentTimeMillis();
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		    String fileName = sdf.format(date);
			String type = file.getOriginalFilename().substring(
					file.getOriginalFilename().lastIndexOf("."));// 取文件格式后缀名
			fileName = fileName + type;// 文件名
			//String path = request.getSession().getServletContext().getRealPath("/upImg/topiCategory/" + fileName);// 存放位置
			String path="E:/file/upImg/topiCategory/"+fileName;
			File destFile = new File(path);
			try {
				// FileUtils.copyInputStreamToFile()这个方法里对IO进行了自动操作，不需要额外的再去关闭IO流
				FileUtils.copyInputStreamToFile(file.getInputStream(), destFile);// 复制临时文件到指定目录下
				return fileName;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return "err";
	}
	
}
