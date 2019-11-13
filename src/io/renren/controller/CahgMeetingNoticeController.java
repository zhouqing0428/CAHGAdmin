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

import io.renren.entity.CahgMeetingNoticeEntity;
import io.renren.entity.SysDeptEntity;
import io.renren.service.CahgMeetingNoticeService;
import io.renren.utils.PageUtils;
import io.renren.utils.R;
import io.renren.utils.ShiroUtils;

/**
 * 会议通知
 * 
 * @author 
 * @email 
 * @date 2017-07-06 13:51:19
 */
@Controller
@RequestMapping("cahgmeetingnotice")
public class CahgMeetingNoticeController {
	@Autowired
	private CahgMeetingNoticeService cahgMeetingNoticeService;
	
	@RequestMapping("/cahgmeetingnotice.html")
	public String list(){
		return "cahgmeetingnotice/cahgmeetingnotice.html";
	}
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("cahgmeetingnotice:list")
	public R list(Integer page, Integer limit){
		Map<String, Object> map = new HashMap<>();
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		
		//查询列表数据
		List<CahgMeetingNoticeEntity> cahgMeetingNoticeList = cahgMeetingNoticeService.queryList(map);
		int total = cahgMeetingNoticeService.queryTotal(map);
		
		PageUtils pageUtil = new PageUtils(cahgMeetingNoticeList, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{meetingNoticeId}")
	@RequiresPermissions("cahgmeetingnotice:info")
	public R info(@PathVariable("meetingNoticeId") Integer meetingNoticeId){
		CahgMeetingNoticeEntity cahgMeetingNotice = cahgMeetingNoticeService.queryObject(meetingNoticeId);
		
		return R.ok().put("cahgMeetingNotice", cahgMeetingNotice);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("cahgmeetingnotice:save")
	public R save(@RequestBody CahgMeetingNoticeEntity cahgMeetingNotice){
		cahgMeetingNotice.setCreateUserId(ShiroUtils.getUserId());
		cahgMeetingNoticeService.save(cahgMeetingNotice);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("cahgmeetingnotice:update")
	public R update(@RequestBody CahgMeetingNoticeEntity cahgMeetingNotice){
		cahgMeetingNotice.setLastUpdateUserId(ShiroUtils.getUserId());
		cahgMeetingNoticeService.update(cahgMeetingNotice);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete")
	@RequiresPermissions("cahgmeetingnotice:delete")
	public R delete(@RequestBody Integer[] meetingNoticeIds){
		cahgMeetingNoticeService.deleteBatch(meetingNoticeIds);
		
		return R.ok();
	}
	
	
	
	//上传文件
	@RequestMapping(value="/upload",method=RequestMethod.POST)
	@ResponseBody
	public R upload(@RequestParam(value="file") MultipartFile  file, @RequestParam(value="meetingNoticeId") String meetingNoticeId, 
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
	    //文件存放路径：应用部署路径/upImg/fileload/当前时间/临时文件名
		String path = request.getSession().getServletContext().getRealPath("/upImg/cahgMeetingNotice/");
		String filePath = path + "\\" + dateFileName;
		//(1)文件保存
		file.transferTo(new File(filePath));
		//(2)文件信息保存至数据库中:路径、临时文件名
		CahgMeetingNoticeEntity cahgMeetingNoticeEntity = null;
		if(meetingNoticeId != null && !"".equals(meetingNoticeId)){
			//查询
			cahgMeetingNoticeEntity = cahgMeetingNoticeService.queryObject(Integer.valueOf(meetingNoticeId));
			cahgMeetingNoticeEntity.setTitle(title);
			//保存到数据库里面的 时间加后缀
			cahgMeetingNoticeEntity.setMeetingFilePath(dateFileName);
			//保存到数据库里面的原文件名
			cahgMeetingNoticeEntity.setMeetingFileName(originalFilename);
			//设置修改人
			cahgMeetingNoticeEntity.setLastUpdateUserId(ShiroUtils.getUserId());//最后修改人的id，
			//更新
			cahgMeetingNoticeService.update(cahgMeetingNoticeEntity);
		}else{
			cahgMeetingNoticeEntity = new CahgMeetingNoticeEntity();
			cahgMeetingNoticeEntity.setTitle(title);
			//保存到数据库里面的 时间加后缀
			cahgMeetingNoticeEntity.setMeetingFilePath(dateFileName);
			//保存到数据库里面的原文件名
			cahgMeetingNoticeEntity.setMeetingFileName(originalFilename);
			//设置新增人
			cahgMeetingNoticeEntity.setCreateUserId(ShiroUtils.getUserId());//新增的人id，时间
			//
			cahgMeetingNoticeService.save(cahgMeetingNoticeEntity);
		}
		R r = R.ok();
		r.put("meetingFileName",originalFilename);
		r.put("meetingFilePath",dateFileName);
		r.put("meetingNoticeId", cahgMeetingNoticeEntity.getMeetingNoticeId());
	    return r;
	}
}
