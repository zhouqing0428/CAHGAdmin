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
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.stereotype.Controller;

import io.renren.entity.CahgJobEntity;
import io.renren.entity.JobDetailEntity;
import io.renren.entity.JobFlow;
import io.renren.entity.SysUserEntity;
import io.renren.service.CahgJobService;
import io.renren.utils.PageUtils;
import io.renren.utils.R;
import io.renren.utils.ShiroUtils;


/**
 * 
 * 
 * @author 
 * @email 
 * @date 2017-07-11 21:19:36
 */
@Controller
@RequestMapping("cahgjob")
public class CahgJobController {
	@Autowired
	private CahgJobService cahgJobService;
	
	@RequestMapping("/cahgjob.html")
	public String list(){
		return "cahgjob/cahgjob.html";
	}
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("cahgjob:list")
	public R list(String status,Integer page, Integer limit){
		Map<String, Object> map = new HashMap<>();
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		map.put("status",status);
		map.put("userId", ShiroUtils.getUserId());
		
		//查询列表数据
		List<CahgJobEntity> cahgJobList = cahgJobService.queryList(map);
		int total = cahgJobService.queryTotal(map);
		//int total = cahgJobService.queryPersonTotal(map);
		
		PageUtils pageUtil = new PageUtils(cahgJobList, total, limit, page);
		return R.ok().put("page", pageUtil);
	}
	
	@ResponseBody
	@RequestMapping("/jobCount")
	public R jobCount(){
		Map<String, Object> map = new HashMap<>();
		map.put("userId", ShiroUtils.getUserId());
		map.put("status", 0); //待办工作
		int total = cahgJobService.queryjobCount(map);
		return R.ok().put("total", total);
	}
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{jobId}")
//	@RequiresPermissions("cahgjob:info")
	public R info(@PathVariable("jobId") Integer jobId){
		CahgJobEntity cahgJob = cahgJobService.queryObject(jobId);
		
		return R.ok().put("cahgJob", cahgJob);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("cahgjob:save")
	public R save(@RequestBody CahgJobEntity cahgJob){
		/*cahgJob.setCreateUserId(ShiroUtils.getUserId());
		cahgJob.setStatus(0);
		cahgJobService.saveAndGetKey(cahgJob);  //保全到工作表里面
		//System.out.println("cahgJob.getJobId()="+cahgJob.getJobId());
		JobFlow jobFlow=new JobFlow();
		jobFlow.setJobId(cahgJob.getJobId());
		jobFlow.setUserId(cahgJob.getFlowUserId()); //流转人id
		jobFlow.setStatus(0);
		jobFlow.setFirstFlow(1);//标记为第一流转人
		cahgJobService.saveJobFlow(jobFlow);  //工作流转信息保全到工作流转表（流转人记录信息）
		jobFlow.setUserId(ShiroUtils.getUserId()); //  发起人
		jobFlow.setStatus(1);  //待办状态
		jobFlow.setFirstFlow(0);
		cahgJobService.saveJobFlow(jobFlow);  //工作流转信息保全到工作流转表（个人发起记录信息）*/
		cahgJob.setCreateUserId(ShiroUtils.getUserId());
		cahgJobService.save(cahgJob);
		return R.ok();
	}
	
	/**
	 * 工作跟进信息
	 * @param jobId
	 * @param request
	 * @return
	 */
	@RequestMapping("/jobDetail")
	public String jobDetail(String jobId,HttpServletRequest request){
		//System.out.println("jobId="+jobId);
		int cahgJobId=Integer.parseInt(jobId);
		CahgJobEntity cahgJob = cahgJobService.queryObject(cahgJobId);
		/*String content=cahgJob.getContent();
		if(content!=null&&!content.isEmpty()){
			cahgJob.setContent(content.replace("<br>", "\r\n"));
		}*/
		SysUserEntity user=cahgJobService.queryFirstFlowUser(cahgJobId); //查询工作第一次流转人
		Map<String, Object> map = new HashMap<>();
		map.put("jobId", jobId); 
		map.put("userId", ShiroUtils.getUserId());  
		List<JobDetailEntity> list = cahgJobService.quryJobDetailList(map); 
		Map mp=cahgJobService.queryUserJobStatus(map);
		request.setAttribute("mp", mp);
		request.setAttribute("job", cahgJob);
		request.setAttribute("user", user);
		request.setAttribute("list", list);
		return "oa/jobDetail.jsp";
	}
	
	/**
	 * 跟进信息添加
	 */
	@ResponseBody
	@RequestMapping("/saveJobDetail")
	public R saveJobDetail(@RequestBody JobDetailEntity jobDetail){
		jobDetail.setCreateUserId(ShiroUtils.getUserId());
		//cahgJob.setStatus(0);
		cahgJobService.saveJobDetail(jobDetail);  // 添加一条跟进消息
		JobFlow jobFlow=new JobFlow();
		jobFlow.setUserId((long) jobDetail.getFlowUserId());
		jobFlow.setJobId(jobDetail.getJobId());
	    jobFlow.setStatus(jobDetail.getStatus());
		Map<String, Object> map = new HashMap<>();
		map.put("status", jobDetail.getStatus());
		map.put("jobId", jobDetail.getJobId());
		map.put("userId", jobDetail.getFlowUserId());
		map.put("lastUpdateUserId", ShiroUtils.getUserId());
		if(jobDetail.getStatus()==1){  //未结束流转
			cahgJobService.updateJobStatus(map);
			cahgJobService.updateJobFlowStatus(map); //  设置跟进人工作状态
			cahgJobService.saveJobFlow(jobFlow);    //流转信息表添加一条流转人记录
			map.put("status",0);  //待办状态
			cahgJobService.updateNextJobFlowStatus(map);  // 设置表 cahg_job_flow 最后流转人 工作状态
		}else { //结束流转
			cahgJobService.updateJobStatus(map);  //设置工作状态已结束 以及最后处理人
			cahgJobService.saveJobFlow(jobFlow);    //流转信息表添加一条流转人记录
			cahgJobService.updateAllJobFlowStatu(map); //  设置跟所有跟进人工作状态已结束
		
		}
		return R.ok();
	}
	


	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("cahgjob:update")
	public R update(@RequestBody CahgJobEntity cahgJob){
		cahgJob.setLastUpdateUserId(ShiroUtils.getUserId());//最后修改人的id，
		cahgJobService.update(cahgJob);
		
		return R.ok();
	}
	/**
	 * 办结
	 * @param cahgJob
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/finish")
	@RequiresPermissions("cahgjob:finish")
	public R finish(@RequestBody CahgJobEntity cahgJob){
		cahgJobService.finish(cahgJob);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete")
	@RequiresPermissions("cahgjob:delete")
	public R delete(@RequestBody Integer[] jobIds){
		cahgJobService.deleteBatch(jobIds);
		
		return R.ok();
	}

	//
	@ResponseBody
	@RequestMapping("/delFile")
	public R delFile(@RequestBody Integer[] jobIds){
		cahgJobService.updateFileNull(jobIds);
		
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
			String path="E:/file/upImg/cahgjob/"+fileName;
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
}
