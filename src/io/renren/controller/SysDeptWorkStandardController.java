package io.renren.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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

import io.renren.entity.SysDeptWorkStandardEntity;
import io.renren.service.SysDeptWorkStandardService;
import io.renren.utils.PageUtils;
import io.renren.utils.R;
import io.renren.utils.ShiroUtils;


/**
 * 
 * 
 * @author 
 * @email 
 * @date 2017-12-14 14:58:24
 */
@Controller
@RequestMapping("sysdeptworkstandard")
public class SysDeptWorkStandardController {
	@Autowired
	private SysDeptWorkStandardService sysDeptWorkStandardService;
	
	@RequestMapping("/sysdeptworkstandard.html")
	public String list(){
		return "sysdeptworkstandard/sysdeptworkstandard.html";
	}
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
//	@RequiresPermissions("sysdeptworkstandard:list")
	public R list(String deptId,Integer page, Integer limit){
		Map<String, Object> map = new HashMap<>();
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		map.put("deptId", deptId);
		
		//查询列表数据
		List<SysDeptWorkStandardEntity> sysDeptWorkStandardList = sysDeptWorkStandardService.queryList(map);
		int total = sysDeptWorkStandardService.queryTotal(map);
		
		PageUtils pageUtil = new PageUtils(sysDeptWorkStandardList, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{workStandardId}")
//	@RequiresPermissions("sysdeptworkstandard:info")
	public R info(@PathVariable("workStandardId") Integer workStandardId){
		SysDeptWorkStandardEntity sysDeptWorkStandard = sysDeptWorkStandardService.queryObject(workStandardId);
		
		return R.ok().put("sysDeptWorkStandard", sysDeptWorkStandard);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
//	@RequiresPermissions("sysdeptworkstandard:save")
	public R save(@RequestBody SysDeptWorkStandardEntity sysDeptWorkStandard){
		sysDeptWorkStandard.setCreateUserId(ShiroUtils.getUserId());
		sysDeptWorkStandardService.save(sysDeptWorkStandard);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
//	@RequiresPermissions("sysdeptworkstandard:update")
	public R update(@RequestBody SysDeptWorkStandardEntity sysDeptWorkStandard){
		sysDeptWorkStandard.setLasteUpdateUserId(ShiroUtils.getUserId());
		sysDeptWorkStandardService.update(sysDeptWorkStandard);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete")
//	@RequiresPermissions("sysdeptworkstandard:delete")
	public R delete(@RequestBody Integer[] workStandardIds){
		sysDeptWorkStandardService.deleteBatch(workStandardIds);
		
		return R.ok();
	}
	
	@ResponseBody
	@RequestMapping("/upFile")
	public String upFile(@RequestParam("file") CommonsMultipartFile file,
			HttpServletRequest request){
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
	    String fileName = sdf.format(date);
		String type = file.getOriginalFilename().substring(
				file.getOriginalFilename().lastIndexOf("."));// 取文件格式后缀名
		fileName = fileName + type;// 文件名
		String path="E:/file/upImg/deptWorkStandard/"+fileName;
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
