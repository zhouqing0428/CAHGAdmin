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

import io.renren.entity.CahgDayInfoEntity;
import io.renren.entity.SysDeptEntity;
import io.renren.service.CahgDayInfoService;
import io.renren.utils.PageUtils;
import io.renren.utils.R;
import io.renren.utils.ShiroUtils;


/**
 * 
 * 
 * @author 
 * @email 
 * @date 2017-06-27 15:44:14
 */
@Controller
@RequestMapping("cahgdayinfo")
public class CahgDayInfoController {
	@Autowired
	private CahgDayInfoService cahgDayInfoService;
	
	@RequestMapping("/cahgdayinfo.html")
	public String list(){
		return "cahgdayinfo/cahgdayinfo.html";
	}
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("cahgdayinfo:list")
	public R list(String dayTitle,String author,Integer page, Integer limit){
		Map<String, Object> map = new HashMap<>();
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		map.put("dayTitle", dayTitle);
		map.put("author", author);
		map.put("dept_id", ShiroUtils.getDeptId());
		
		//查询列表数据
		List<CahgDayInfoEntity> cahgDayInfoList = cahgDayInfoService.queryList(map);
		int total = cahgDayInfoService.queryTotal(map);
		
		PageUtils pageUtil = new PageUtils(cahgDayInfoList, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{dayId}")
	@RequiresPermissions("cahgdayinfo:info")
	public R info(@PathVariable("dayId") Integer dayId){
		CahgDayInfoEntity cahgDayInfo = cahgDayInfoService.queryObject(dayId);
		
		return R.ok().put("cahgDayInfo", cahgDayInfo);
	}
	
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("cahgdayinfo:save")
	public R save(@RequestBody CahgDayInfoEntity cahgDayInfo){
		cahgDayInfo.setCreateUserId(ShiroUtils.getUserEntity().getUserId());
		cahgDayInfo.setDeptId(ShiroUtils.getUserEntity().getDeptId());
		cahgDayInfoService.save(cahgDayInfo);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("cahgdayinfo:update")
	public R update(@RequestBody CahgDayInfoEntity cahgDayInfo){
		cahgDayInfo.setLastUpdateUserId(ShiroUtils.getUserEntity().getUserId());
		cahgDayInfoService.update(cahgDayInfo);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete")
	@RequiresPermissions("cahgdayinfo:delete")
	public R delete(@RequestBody Integer[] dayIds){
		cahgDayInfoService.deleteBatch(dayIds);
		
		return R.ok();
	}
	
	
	//
	@ResponseBody
	@RequestMapping("/delFile")
	public R delFile(@RequestBody Integer[] dayId){
		cahgDayInfoService.updateFileNull(dayId);
		
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
			String path="E:/file/upImg/cahgDayInfo/"+fileName;
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
	public R upload(@RequestParam(value="file") MultipartFile  file, @RequestParam(value="dayId") String dayId, 
			@RequestParam(value="dayTitle") String dayTitle,HttpServletRequest request) throws IOException{
		dayTitle=java.net.URLDecoder.decode(dayTitle,"utf-8");
		String originalFilename = file.getOriginalFilename();
		String suffixFileName = originalFilename.substring(originalFilename.indexOf(".") + 1);
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
		String datePath = sdf.format(date);
	    String dateFileName = datePath + "." + suffixFileName;
		String path = request.getSession().getServletContext().getRealPath("/upImg/cahgDayInfo/");
		String filePath = path + "\\" + dateFileName;
		file.transferTo(new File(filePath));//传送文件至文件夹
		CahgDayInfoEntity cahgDayInfoEntity = null;
		if(dayId != null && !"".equals(dayId)){//id 不为空时则修改
			cahgDayInfoEntity = cahgDayInfoService.queryObject(Integer.valueOf(dayId));
			cahgDayInfoEntity.setDayName(dateFileName);
			cahgDayInfoEntity.setDayTitle(dayTitle);//新增是set 标题
			cahgDayInfoEntity.setDayFileName(originalFilename);
			//设置修改人
			cahgDayInfoEntity.setLastUpdateUserId(ShiroUtils.getUserId());//最后修改人的id，
			cahgDayInfoService.update(cahgDayInfoEntity);
		}else{//id 为空时则是新增
			cahgDayInfoEntity = new CahgDayInfoEntity();
			cahgDayInfoEntity.setDayTitle(dayTitle);//新增是set 标题
			cahgDayInfoEntity.setDayName(dateFileName);//文件原名
			cahgDayInfoEntity.setDayFileName(originalFilename);//文件时间加后缀名
			//设置新增人
			cahgDayInfoEntity.setCreateUserId(ShiroUtils.getUserId());//新增的人id，时间
			cahgDayInfoEntity.setDeptId(ShiroUtils.getUserEntity().getDeptId());
			cahgDayInfoService.save(cahgDayInfoEntity);//保存
		}
		R r = R.ok();
		r.put("dayName",originalFilename);
		r.put("dayFileName",dateFileName);
		r.put("dayId", cahgDayInfoEntity.getDayId());
	    return r;
	}
	
}
