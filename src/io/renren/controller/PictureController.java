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

import io.renren.entity.CahgImgNewsEntity;
import io.renren.entity.PictureEntity;
import io.renren.entity.SysDeptEntity;
import io.renren.service.PictureService;
import io.renren.utils.PageUtils;
import io.renren.utils.R;
import io.renren.utils.ShiroUtils;

import org.apache.commons.io.FileUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;


/**
 * 
 * 
 * @author 
 * @email 
 * @date 2017-07-11 21:19:36
 */
@Controller
@RequestMapping("picture")
public class PictureController {
	@Autowired
	private PictureService pictureService;
	
	@RequestMapping("/picture.html")
	public String list(){
		return "picture/picture.html";
	}
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("picture:list")
	public R list(Integer page, Integer limit){
		Map<String, Object> map = new HashMap<>();
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		
		//查询列表数据
		List<PictureEntity> pictureList = pictureService.listUser(map);
		int total = pictureService.queryTotalUser(map);
		
		PageUtils pageUtil = new PageUtils(pictureList, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	
	/**
	 * 查询
	 */
	@ResponseBody
	@RequestMapping("/info/{id}")
	@RequiresPermissions("picture:info")
	public R info(@PathVariable("id") Integer id){
		PictureEntity pictureEntity = pictureService.listUserId(id);
		System.out.println(id);
		return R.ok().put("pictureEntity", pictureEntity);
	}
	
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("picture:update")
	public R update(@RequestBody PictureEntity pictureEntity){
		pictureEntity.setLastUpdateUserId(ShiroUtils.getUserId());//最后修改人的id，
		pictureEntity.setLastUpdateDate(new Date());
		pictureService.update(pictureEntity);
		
		return R.ok();
	}
	
	
	//替换文件
	@RequestMapping(value="/upload",method=RequestMethod.POST)
	@ResponseBody
	public R upload(@RequestParam(value="file") MultipartFile  file, @RequestParam(value="imgId") String imgId, 
			@RequestParam(value="sysRank") String sysRank,HttpServletRequest request) throws IOException{
		/*name=java.net.URLDecoder.decode(name,"utf-8");*/
		//删除原文件
		PictureEntity pictureEntity = pictureService.listUserId(Integer.valueOf(imgId));
		
		
		
		//文件名
		String originalFilename = file.getOriginalFilename();
		//截取后缀名
		String suffixFileName = originalFilename.substring(originalFilename.indexOf(".") + 1);
		//生成临时文件名：当前时间+后缀名
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
		String datePath = sdf.format(date);
	    String dateFileName = datePath + "." + suffixFileName;
	    //文件存放路径：应用部署路径/upImg/fileload/当前时间/临时文件名
		String path = request.getSession().getServletContext().getRealPath("/upImg/file/");
		String filePath = path + "\\" + dateFileName;
		//(1)文件保存
		file.transferTo(new File(filePath));
		System.out.println(filePath);
		//(2)文件信息更新至数据库中:文件名、临时文件名、路径
		pictureEntity.setImgName(originalFilename);
		pictureEntity.setImgNameTemp(dateFileName);
		pictureEntity.setImgUrl(path);
		pictureEntity.setLastUpdateUserId(ShiroUtils.getUserId());
		pictureEntity.setLastUpdateDate(new Date());
		//更新
		pictureService.update(pictureEntity);
		
		R r = R.ok();
		r.put("fileNames",originalFilename);
		r.put("dataPath",dateFileName);
		r.put("imgId", pictureEntity.getImgId());
	    return r;
	}
	
}
