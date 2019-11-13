package io.renren.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.stereotype.Controller;

import io.renren.entity.SysActivityEntity;
import io.renren.entity.SysTestEntity;
import io.renren.service.SysActivityService;
import io.renren.utils.ObjectExcelRead;
import io.renren.utils.PageUtils;
import io.renren.utils.R;
import io.renren.utils.ShiroUtils;

/**
 * 
 * 
 * @author
 * @email
 * @date
 */
@Controller
@RequestMapping("sysactivity")
public class SysActivityController {
	@Autowired
	private SysActivityService sysActivityService;

	@RequestMapping("/sysactivity.html")
	public String list() {
		return "sysactivity/sysactivity.html";
	}

	/*
	 * 查询活动标题 及id
	 */
	@ResponseBody
	@RequestMapping("/activityList")
	// public LinkedHashSet<SysActivityEntity> activityList(){
	public LinkedHashSet<SysActivityEntity> activityList() {
		LinkedHashSet<SysActivityEntity> sysActivityList = sysActivityService
				.activityList();
		// return R.ok().put("data", sysActivityList);
		return sysActivityList;
	}

	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("sysactivity:list")
	public R list(Integer page, Integer limit) {
		Map<String, Object> map = new HashMap<>();
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);

		// 查询列表数据
		List<SysActivityEntity> sysActivityList = sysActivityService
				.queryList(map);
		int total = sysActivityService.queryTotal(map);
		PageUtils pageUtil = new PageUtils(sysActivityList, total, limit, page);

		return R.ok().put("page", pageUtil);
	}

	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{activityId}")
	@RequiresPermissions("sysactivity:info")
	public R info(@PathVariable("activityId") Long activityId) {
		SysActivityEntity sysActivity = sysActivityService
				.queryObject(activityId);

		return R.ok().put("sysActivity", sysActivity);
	}

	/**
	 * 保存
	 * 
	 * @throws ParseException
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("sysactivity:save")
	// public R save(@RequestBody SysActivityEntity sysActivity){
	public R save(HttpServletRequest request) throws ParseException {
		SysActivityEntity sysActivity = new SysActivityEntity();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String title = request.getParameter("title");
		String num = request.getParameter("number");
		Integer number = null;
		if (null != num && num != "") {
			number = Integer.valueOf(num);
		}
		String expense = request.getParameter("expense");
		String sdate = request.getParameter("strartDate");
		Date strartDate = null;
		Date endDate = null;
		if (null != sdate && sdate != "") {
			strartDate = sdf.parse(sdate);
		}
		String edate = request.getParameter("endDate");
		if (null != edate && edate != "") {
			endDate = sdf.parse(edate);
		}
		String cateId = request.getParameter("actiCateId");
		Long actiCateId = 0L;
		if (null != cateId && cateId != "") {
			actiCateId = Long.parseLong(cateId);
		}
		String effect = request.getParameter("effect");
		String content = request.getParameter("content");
		sysActivity.setTitle(title);
		sysActivity.setNumber(number);
		sysActivity.setExpense(expense);
		sysActivity.setStrartDate(strartDate);
		sysActivity.setEndDate(endDate);
		sysActivity.setEffect(effect);
		sysActivity.setContent(content);
		sysActivity.setNote(request.getParameter("note"));
		sysActivity.setActiImg(request.getParameter("actiImg"));
		sysActivity.setActiCateId(actiCateId);
		sysActivityService.save(sysActivity);
		return R.ok();
	}

	/**
	 * 修改
	 * 
	 * @throws ParseException
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("sysactivity:update")
	public R update(HttpServletRequest request) throws ParseException {
		SysActivityEntity sysActivity = new SysActivityEntity();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String acid = request.getParameter("activityId");
		// String activityId = request.getParameter("activityId");
		Long activityId = 0L;
		if (null != acid && acid != "") {
			activityId = Long.parseLong(acid);
		}
		String cateId = request.getParameter("actiCateId");
		Long actiCateId = 0L;
		if (null != cateId && cateId != "") {
			actiCateId = Long.parseLong(cateId);
		}
		String title = request.getParameter("title");
		String num = request.getParameter("number");
		Integer number = null;
		if (null != num && num != "") {
			number = Integer.valueOf(num);
		}
		String expense = request.getParameter("expense");
		String sdate = request.getParameter("strartDate");
		Date strartDate = null;
		Date endDate = null;
		if (null != sdate && sdate != "") {
			strartDate = sdf.parse(sdate);
		}
		String edate = request.getParameter("endDate");
		if (null != edate && edate != "") {
			endDate = sdf.parse(edate);
		}
		String effect = request.getParameter("effect");
		String content = request.getParameter("content");
		String actiImg = request.getParameter("actiImg");  //标题图片
		System.out.println("actiImg="+actiImg);
		sysActivity.setActivityId(activityId);
		sysActivity.setTitle(title);
		sysActivity.setNumber(number);
		sysActivity.setExpense(expense);
		sysActivity.setStrartDate(strartDate);
		sysActivity.setEndDate(endDate);
		sysActivity.setEffect(effect);
		sysActivity.setContent(content);
		sysActivity.setActiImg(actiImg);
		sysActivity.setNote(request.getParameter("note"));  //备注
		sysActivity.setActiCateId(actiCateId);

		sysActivityService.update(sysActivity);

		return R.ok();
	}

	/**
	 * 上传活动图片  @RequestParam() 必须使用 html name 属性 id不起作用
	 */
	@ResponseBody
	@RequestMapping("/upActiImg")
	public String upActiImg(@RequestParam("upActiImg") CommonsMultipartFile file,
			HttpServletRequest request,HttpServletResponse response) { // /
		if (!file.isEmpty()) {
			long startTime = System.currentTimeMillis();
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		    String fileName = sdf.format(date);
			String type = file.getOriginalFilename().substring(
					file.getOriginalFilename().lastIndexOf("."));// 取文件格式后缀名
			fileName = fileName + type;// 文件名
			String path = request.getSession().getServletContext().getRealPath("/upImg/activity/" + fileName);// 存放位置
			File destFile = new File(path);
			System.out.println(path);
			try {
				// FileUtils.copyInputStreamToFile()这个方法里对IO进行了自动操作，不需要额外的再去关闭IO流
				FileUtils.copyInputStreamToFile(file.getInputStream(), destFile);// 复制临时文件到指定目录下
				System.out.println(fileName);
				return fileName;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return "err";
	}
/*	public void upActiImg(@RequestParam("upActiImg") CommonsMultipartFile file,
			HttpServletRequest request,HttpServletResponse response) { // /
		response.setCharacterEncoding("UTF-8");
		if (!file.isEmpty()) {
			long startTime = System.currentTimeMillis();
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		    String fileName = sdf.format(date);
			String type = file.getOriginalFilename().substring(
					file.getOriginalFilename().lastIndexOf("."));// 取文件格式后缀名
			fileName = fileName + type;// 文件名
			String path = request.getSession().getServletContext().getRealPath("/upImg/activity/" + fileName);// 存放位置
			File destFile = new File(path);
			System.out.println(path);
			try {
				// FileUtils.copyInputStreamToFile()这个方法里对IO进行了自动操作，不需要额外的再去关闭IO流
				FileUtils.copyInputStreamToFile(file.getInputStream(), destFile);// 复制临时文件到指定目录下
				System.out.println(fileName);
				
				JSONObject json = new JSONObject();json.put("status", "success");
				json.put("name",fileName.toString());response.getWriter().print(json.toString());

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}*/

	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping(value = "/delete")
	@RequiresPermissions("sysactivity:delete")
	public R delete(@RequestBody Long[] activityIds) {
		sysActivityService.deleteBatch(activityIds);

		return R.ok();
	}

}
