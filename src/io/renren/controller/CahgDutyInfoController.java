package io.renren.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.stereotype.Controller;

import io.renren.entity.CahgDutyInfoEntity;
import io.renren.entity.SysDeptEntity;
import io.renren.service.CahgDutyInfoService;
import io.renren.utils.PageUtils;
import io.renren.utils.R;


/**
 * 值班通知
 * 
 * @author 
 * @email 
 * @date 2017-07-26 14:48:27
 */
@Controller
@RequestMapping("cahgdutyinfo")
public class CahgDutyInfoController {
	@Autowired
	private CahgDutyInfoService cahgDutyInfoService;
	
	@RequestMapping("/cahgdutyinfo.html")
	public String list(){
		return "cahgdutyinfo/cahgdutyinfo.html";
	}
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("cahgdutyinfo:list")
	public R list(Integer page, Integer limit){
		Map<String, Object> map = new HashMap<>();
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		
		//查询列表数据
		List<CahgDutyInfoEntity> cahgDutyInfoList = cahgDutyInfoService.queryList(map);
		int total = cahgDutyInfoService.queryTotal(map);
		
		PageUtils pageUtil = new PageUtils(cahgDutyInfoList, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{dutyInfoId}")
	//@RequiresPermissions("cahgdutyinfo:info")
	public R info(@PathVariable("dutyInfoId") Integer dutyInfoId){
		CahgDutyInfoEntity cahgDutyInfo = cahgDutyInfoService.queryObject(dutyInfoId);
		
		return R.ok().put("cahgDutyInfo", cahgDutyInfo);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("cahgdutyinfo:save")
	public R save(@RequestBody CahgDutyInfoEntity cahgDutyInfo){
		cahgDutyInfoService.save(cahgDutyInfo);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
//	@RequiresPermissions("cahgdutyinfo:update")
	public R update(@RequestBody CahgDutyInfoEntity cahgDutyInfo){
		cahgDutyInfoService.update(cahgDutyInfo);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete")
	@RequiresPermissions("cahgdutyinfo:delete")
	public R delete(@RequestBody Integer[] dutyInfoIds){
		cahgDutyInfoService.deleteBatch(dutyInfoIds);
		
		return R.ok();
	}
	
	
	//文件上传：
	@RequestMapping(value="/upload",method=RequestMethod.POST)
	@ResponseBody
	public R upload(@RequestParam(value="file") MultipartFile  file, @RequestParam(value="dutyInfoId") String dutyInfoId, 
			HttpServletRequest request) throws IOException{
	
		String originalFilename = file.getOriginalFilename();
		String suffixFileName = originalFilename.substring(originalFilename.indexOf(".") + 1);
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
		String datePath = sdf.format(date);
	    String dateFileName = datePath + "." + suffixFileName;
	    //文件存放路径：应用部署路径/upImg/fileload/当前时间/临时文件名
		String path = request.getSession().getServletContext().getRealPath("/upImg/cahgDutyInfo/");
		String filePath = path + "\\" + dateFileName;
		//(1)文件保存
		file.transferTo(new File(filePath));
		//(2)文件信息保存至数据库中:路径、临时文件名
		R r = R.ok();
		r.put("dutyFileName",originalFilename);
		r.put("dutyFilePath",dateFileName);
		
	    return r;
	}
	
}
