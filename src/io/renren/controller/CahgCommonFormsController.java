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

import io.renren.entity.CahgCommonFormsEntity;
import io.renren.service.CahgCommonFormsService;
import io.renren.utils.PageUtils;
import io.renren.utils.R;
import io.renren.utils.ShiroUtils;


/**
 * 
 * 
 * @author 
 * @email 
 * @date 2017-07-24 17:08:54
 */
@Controller
@RequestMapping("cahgcommonforms")
public class CahgCommonFormsController {
	@Autowired
	private CahgCommonFormsService cahgCommonFormsService;
	
	@RequestMapping("/cahgcommonforms.html")
	public String list(){
		return "cahgcommonforms/cahgcommonforms.html";
	}
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("cahgcommonforms:list")
	public R list(Integer page, Integer limit){
		Map<String, Object> map = new HashMap<>();
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		map.put("dept_id", ShiroUtils.getDeptId());
		
		//查询列表数据
		List<CahgCommonFormsEntity> cahgCommonFormsList = cahgCommonFormsService.queryList(map);
		int total = cahgCommonFormsService.queryTotal(map);
		
		PageUtils pageUtil = new PageUtils(cahgCommonFormsList, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{commonFormsId}")
	@RequiresPermissions("cahgcommonforms:info")
	public R info(@PathVariable("commonFormsId") Integer commonFormsId){
		CahgCommonFormsEntity cahgCommonForms = cahgCommonFormsService.queryObject(commonFormsId);
		
		return R.ok().put("cahgCommonForms", cahgCommonForms);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("cahgcommonforms:save")
	public R save(@RequestBody CahgCommonFormsEntity cahgCommonForms){
		cahgCommonForms.setCreateUserId(ShiroUtils.getUserId());
		cahgCommonForms.setDeptId(ShiroUtils.getUserEntity().getDeptId());
		cahgCommonFormsService.save(cahgCommonForms);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("cahgcommonforms:update")
	public R update(@RequestBody CahgCommonFormsEntity cahgCommonForms){
		cahgCommonForms.setLastUpdateUserId(ShiroUtils.getUserId());
		cahgCommonFormsService.update(cahgCommonForms);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete")
	@RequiresPermissions("cahgcommonforms:delete")
	public R delete(@RequestBody Integer[] commonFormsIds){
		cahgCommonFormsService.deleteBatch(commonFormsIds);
		
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
			//String path = request.getSession().getServletContext().getRealPath("/upImg/commonforms/" + fileName);// 存放位置
			String path="E:/file/upImg/commonforms/"+fileName;
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
