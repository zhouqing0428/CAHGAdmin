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
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import io.renren.entity.CahgStyleEntity;
import io.renren.entity.SysDeptEntity;
import io.renren.service.CahgStyleService;
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
@RequestMapping("cahgstyle")
public class CahgStyleController {
	@Autowired
	private CahgStyleService cahgStyleService;
	@Autowired
	private SysDeptService sysDeptService;
	
	@RequestMapping("/cahgstyle.html")
	public String list(){
		return "cahgstyle/cahgstyle.html";
	}
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("cahgstyle:list")
	public R list(String title, String author, String stick, Integer page, Integer limit) {
		Map<String, Object> map = new HashMap<>();
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		map.put("styleTitle", title);
		map.put("author", author);
		map.put("dept_id", ShiroUtils.getDeptId());
		if (!StringUtils.isEmpty(stick)) {
			map.put("styleStick", stick);
		}
		
		// 查询列表数据
		List<CahgStyleEntity> cahgStyleList = cahgStyleService.queryList(map);
		int total = cahgStyleService.queryTotal(map);

		PageUtils pageUtil = new PageUtils(cahgStyleList, total, limit, page);

		return R.ok().put("page", pageUtil);
	}

	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{styleId}")
	@RequiresPermissions("cahgstyle:info")
	public R info(@PathVariable("styleId") Integer styleId){
		CahgStyleEntity cahgStyle = cahgStyleService.queryObject(styleId);
		return R.ok().put("cahgStyle", cahgStyle);
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
	@RequestMapping("/stick/{styleId}")
	@RequiresPermissions("cahgstyle:stick")
	public R stick(@PathVariable("styleId") Integer styleId){
		cahgStyleService.unStick();  
		cahgStyleService.stick(styleId);
		return R.ok();
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("cahgstyle:save")
	public R save(@RequestBody CahgStyleEntity cahgStyle){

		cahgStyleService.save(cahgStyle);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("cahgstyle:update")
	public R update(@RequestBody CahgStyleEntity cahgStyle){
		cahgStyle.setLastUpdateUserId(ShiroUtils.getUserEntity().getUserId());
		cahgStyleService.update(cahgStyle);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete")
	@RequiresPermissions("cahgstyle:delete")
	public R delete(@RequestBody Integer[] styleIds){
		cahgStyleService.deleteBatch(styleIds);
		return R.ok();
	}
	
	/**
	 * 上传图片  @RequestParam() 必须使用 html name 属性 id不起作用
	 */
	@ResponseBody
	@RequestMapping("/upStyleUrl")
	public String upActiImg(@RequestParam("styleUrl") CommonsMultipartFile file,
			HttpServletRequest request,HttpServletResponse response) { // /
		if (!file.isEmpty()) {
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		    String fileName = sdf.format(date);
			String type = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));// 取文件格式后缀名
			fileName = fileName + type;// 文件名
			String path="d:/file/upStyle/style/"+fileName;
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
	
	/**
	 * 显示
	 */
	@ResponseBody
	@RequestMapping("/show")
	@RequiresPermissions("cahgstyle:show")
	public R show(@RequestBody Integer[] styleIds){
		cahgStyleService.updateStatusShow(styleIds);
		
		return R.ok();
	}
	/**
	 * 不显示
	 */
	@ResponseBody
	@RequestMapping("/unshow")
	@RequiresPermissions("cahgstyle:unshow")
	public R unshow(@RequestBody Integer[] styleIds){
		cahgStyleService.updateStatusUnShow(styleIds);
		
		return R.ok();
	}
	
}
