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

import io.renren.entity.CahgAfficheEntity;
import io.renren.entity.SysDeptEntity;
import io.renren.service.CahgAfficheService;
import io.renren.utils.PageUtils;
import io.renren.utils.R;
import io.renren.utils.ShiroUtils;


/**
 * 公示公告
 * 
 * @author 
 * @email 
 * @date 2017-07-06 17:50:57
 */
@Controller
@RequestMapping("cahgaffiche")
public class CahgAfficheController {
	@Autowired
	private CahgAfficheService cahgAfficheService;
	
	@RequestMapping("/cahgaffiche.html")
	public String list(){
		return "cahgaffiche/cahgaffiche.html";
	}
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("cahgaffiche:list")
	public R list(Integer page, Integer limit){
		Map<String, Object> map = new HashMap<>();
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		map.put("dept_id", ShiroUtils.getDeptId());
		
		//查询列表数据
		List<CahgAfficheEntity> cahgAfficheList = cahgAfficheService.queryList(map);
		int total = cahgAfficheService.queryTotal(map);
		
		PageUtils pageUtil = new PageUtils(cahgAfficheList, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{afficheId}")
	@RequiresPermissions("cahgaffiche:info")
	public R info(@PathVariable("afficheId") Integer afficheId){
		CahgAfficheEntity cahgAffiche = cahgAfficheService.queryObject(afficheId);
		
		return R.ok().put("cahgAffiche", cahgAffiche);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("cahgaffiche:save")
	public R save(@RequestBody CahgAfficheEntity cahgAffiche){
		cahgAffiche.setCreateUserId(ShiroUtils.getUserId());
		cahgAffiche.setDeptId(ShiroUtils.getUserEntity().getDeptId());
		cahgAfficheService.save(cahgAffiche);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("cahgaffiche:update")
	public R update(@RequestBody CahgAfficheEntity cahgAffiche){
		cahgAffiche.setLastUpdateUserId(ShiroUtils.getUserId());
		cahgAfficheService.update(cahgAffiche);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete")
	@RequiresPermissions("cahgaffiche:delete")
	public R delete(@RequestBody Integer[] afficheIds){
		cahgAfficheService.deleteBatch(afficheIds);
		
		return R.ok();
	}

	//
	@ResponseBody
	@RequestMapping("/delFile")
	public R delFile(@RequestBody Integer[] afficheIds){
		cahgAfficheService.updateFileNull(afficheIds);
		
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
			String path="E:/file/upImg/cahgAffiche/"+fileName;
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
	public R upload(@RequestParam(value="file") MultipartFile  file, @RequestParam(value="afficheId") String afficheId, 
			@RequestParam(value="title") String title,HttpServletRequest request) throws IOException{
		title=java.net.URLDecoder.decode(title,"utf-8");
		//文件名
		String originalFilename = file.getOriginalFilename();
		//截取后缀名
		String suffixFileName = originalFilename.substring(originalFilename.indexOf(".") + 1);
		//生成临时文件名：当前时间+后缀名
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
		String datePath = sdf.format(date);
	    String dateFileName = datePath + "." + suffixFileName;
	    //文件存放路径：应用部署路径/upImg/cahgAffiche/当前时间/临时文件名
		String path = request.getSession().getServletContext().getRealPath("/upImg/cahgAffiche/");
		String filePath = path + "\\" + dateFileName;
		//(1)文件保存
		file.transferTo(new File(filePath));
		//(2)文件信息保存至数据库中:路径、临时文件名
		CahgAfficheEntity cahgAfficheEntity = null;
		if(afficheId != null && !"".equals(afficheId)){
			//查询
			cahgAfficheEntity = cahgAfficheService.queryObject(Integer.valueOf(afficheId));
			cahgAfficheEntity.setTitle(title);
			//保存到数据库里面的 时间加后缀
			cahgAfficheEntity.setAfficheFilePath(dateFileName);
			//保存到数据库里面的原文件名
			cahgAfficheEntity.setAfficheFileName(originalFilename);
			//设置修改人
			cahgAfficheEntity.setLastUpdateUserId(ShiroUtils.getUserId());//最后修改人的id，
			//更新
			cahgAfficheService.update(cahgAfficheEntity);
		}else{ 
			cahgAfficheEntity = new CahgAfficheEntity();
			//设置名称
			cahgAfficheEntity.setTitle(title);
			//保存到数据库里面的 时间加后缀
			cahgAfficheEntity.setAfficheFilePath(dateFileName);
			//保存到数据库里面的原文件名
			cahgAfficheEntity.setAfficheFileName(originalFilename);
			//设置新增人
			cahgAfficheEntity.setCreateUserId(ShiroUtils.getUserId());//新增的人id，时间
			cahgAfficheEntity.setDeptId(ShiroUtils.getUserEntity().getDeptId());

			//更新
			cahgAfficheService.save(cahgAfficheEntity);
		}
		R r = R.ok();
		r.put("afficheFileName",originalFilename);
		r.put("afficheFilePath",dateFileName);
		r.put("afficheId", cahgAfficheEntity.getAfficheId());
	    return r;
	}
	
}
