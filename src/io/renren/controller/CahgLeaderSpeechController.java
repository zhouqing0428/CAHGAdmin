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

import io.renren.entity.CahgLeaderSpeechEntity;
import io.renren.service.CahgLeaderSpeechService;
import io.renren.utils.PageUtils;
import io.renren.utils.R;
import io.renren.utils.ShiroUtils;


/**
 * 
 * 领导讲话
 * @author 
 * @email 
 * @date 2017-06-29 14:58:49
 */
@Controller
@RequestMapping("cahgleaderspeech")
public class CahgLeaderSpeechController {
	@Autowired
	private CahgLeaderSpeechService cahgLeaderSpeechService;
	
	@RequestMapping("/cahgleaderspeech.html")
	public String list(){
		return "cahgleaderspeech/cahgleaderspeech.html";
	}
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("cahgleaderspeech:list")
	public R list(Integer page, Integer limit){
		Map<String, Object> map = new HashMap<>();
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		
		//查询列表数据
		List<CahgLeaderSpeechEntity> cahgLeaderSpeechList = cahgLeaderSpeechService.queryList(map);
		int total = cahgLeaderSpeechService.queryTotal(map);
		
		PageUtils pageUtil = new PageUtils(cahgLeaderSpeechList, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{leaderSpeechId}")
	@RequiresPermissions("cahgleaderspeech:info")
	public R info(@PathVariable("leaderSpeechId") Integer leaderSpeechId){
		CahgLeaderSpeechEntity cahgLeaderSpeech = cahgLeaderSpeechService.queryObject(leaderSpeechId);
		
		return R.ok().put("cahgLeaderSpeech", cahgLeaderSpeech);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("cahgleaderspeech:save")
	public R save(@RequestBody CahgLeaderSpeechEntity cahgLeaderSpeech){
		cahgLeaderSpeech.setCreateUserId(ShiroUtils.getUserId());
		cahgLeaderSpeechService.save(cahgLeaderSpeech);
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("cahgleaderspeech:update")
	public R update(@RequestBody CahgLeaderSpeechEntity cahgLeaderSpeech){
		cahgLeaderSpeech.setLastUpdateUserId(ShiroUtils.getUserId());
		cahgLeaderSpeechService.update(cahgLeaderSpeech);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete")
	@RequiresPermissions("cahgleaderspeech:delete")
	public R delete(@RequestBody Integer[] leaderSpeechIds){
		cahgLeaderSpeechService.deleteBatch(leaderSpeechIds);
		
		return R.ok();
	}
	
	/**
	 * 上传文件  @RequestParam() 必须使用 html name 属性 id不起作用
	 */
	@ResponseBody
	@RequestMapping("/upFile")
	public String upActiImg(@RequestParam("file") CommonsMultipartFile file,
			HttpServletRequest request,HttpServletResponse response) { // /
			//long startTime = System.currentTimeMillis();
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		    String fileName = sdf.format(date);
			String type = file.getOriginalFilename().substring(
					file.getOriginalFilename().lastIndexOf("."));// 取文件格式后缀名
			fileName = fileName + type;// 文件名
			//String path = request.getSession().getServletContext().getRealPath("/upImg/file/" + fileName);// 存放位置
			String path="E:/file/upImg/file/"+fileName;
			File destFile = new File(path);
			//System.out.println(path);
			try {
				// FileUtils.copyInputStreamToFile()这个方法里对IO进行了自动操作，不需要额外的再去关闭IO流
				FileUtils.copyInputStreamToFile(file.getInputStream(), destFile);// 复制临时文件到指定目录下
				System.out.println(fileName);
				return fileName;
			} catch (IOException e) {
				e.printStackTrace();
			}
		return "err";
	}
	
}
