package io.renren.controller;

import io.renren.entity.SysDeptEntity;
import io.renren.service.SysDeptService;
import io.renren.utils.PageUtils;
import io.renren.utils.R;
import io.renren.utils.ShiroUtils;

import java.io.File;
import java.io.IOException;
import java.lang.ProcessBuilder.Redirect;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
 * @date 2017-06-23 16:05:45
 */
@Controller
@RequestMapping("sysdept")
public class SysDeptController {
	  
	@Autowired
	private SysDeptService sysDeptService;
	
	@RequestMapping("/sysdept.html")
	public String list(){
		System.out.println("dept  list");
		return "sysdept/sysdept.html";
	}
	
	@RequestMapping("/deptRegime")
	public String deptRegime(String deptId,HttpServletRequest request){
		SysDeptEntity dept= sysDeptService.queryObject(Integer.parseInt(deptId));
		request.setAttribute("deptId", deptId);
		request.setAttribute("deptName", dept.getName());
		return "oa/sysdeptregime.jsp";
	}
	
	
	@RequestMapping("/deptWorkStandard")
	public String deptWorkStandard(String deptId,HttpServletRequest request){
		SysDeptEntity dept= sysDeptService.queryObject(Integer.parseInt(deptId));
		request.setAttribute("deptId", deptId);
		request.setAttribute("deptName", dept.getName());
		return "oa/sysdeptworkstandard.jsp";
	}
	
	
	/**
	 * 制度建设 列表
	 */
	@ResponseBody
	@RequestMapping("/regimeList")
	public R regimeList(Integer page, Integer limit){
		Map<String, Object> map = new HashMap<>();
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		Long userId=ShiroUtils.getUserId();
		if(userId!=1){  //非管理员 查询该用户的部门
			int deptId=ShiroUtils.getUserEntity().getDeptId();
			map.put("deptId",deptId);
		}
		//查询列表数据
		List<SysDeptEntity> sysDeptList = sysDeptService.queryList(map);
		int total = sysDeptService.queryTotal(map);
		
		PageUtils pageUtil = new PageUtils(sysDeptList, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("sysdept:list")
	public R list(Integer page, Integer limit){
		Map<String, Object> map = new HashMap<>();
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		//查询列表数据
		List<SysDeptEntity> sysDeptList = sysDeptService.queryList(map);
		int total = sysDeptService.queryTotal(map);
		
		PageUtils pageUtil = new PageUtils(sysDeptList, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{deptId}")
//	@RequiresPermissions("sysdept:info")
	public R info(@PathVariable("deptId") Integer deptId){
		SysDeptEntity sysDept = sysDeptService.queryObject(deptId);
		/*String duty=sysDept.getDuty();
		String regime=sysDept.getRegime();
		String workStandard=sysDept.getWorkStandard();
		if(duty!=null&&!duty.isEmpty()){
			sysDept.setDuty(duty.replace("<br>", "\r\n"));
		}
		if(regime!=null&&!regime.isEmpty()){
			sysDept.setRegime(regime.replace("<br>", "\r\n"));
		}
		if(workStandard!=null&&!workStandard.isEmpty()){
			sysDept.setWorkStandard(workStandard.replace("<br>", "\r\n"));
		}*/
		return R.ok().put("sysDept", sysDept);
	}
	//通讯录
		@RequestMapping("/answerInfo")
		public String answerInfo(HttpServletRequest request){
			String name = request.getParameter("name");
			String sysContent = request.getParameter("sysContent");
			SysDeptEntity sysDept=null;
			if(sysContent!=null&&!sysContent.isEmpty()){
				int deptId=Integer.parseInt(sysContent);
				sysDept = sysDeptService.queryObject(deptId);
				request.setAttribute("sysContent", sysContent);
				request.setAttribute("name",name);
				request.setAttribute("sysDept", sysDept);
			}
			return "sys/sysDept.jsp";
		}
		/**
		 * 查询通讯录
		 */
		@ResponseBody
		@RequestMapping("/selectListContent/{deptId}")
		public R selectListContent(@PathVariable("deptId") Integer deptId){
			//查询列表数据
			SysDeptEntity sysDept = sysDeptService.queryListContent(deptId);
			return R.ok().put("sysDept", sysDept);
		}
		

		
		/**
		 * 修改
		 */
		@ResponseBody
		@RequestMapping("/updateContent")
		@RequiresPermissions("sysdept:updateContent")
		public R updateContent(@RequestBody SysDeptEntity sysDept){
			sysDept.setLastUpdateUserId(ShiroUtils.getUserId());
			sysDeptService.updateContent(sysDept);
			
			return R.ok();
		}
		
	/**
	 * 部门列表
	 */
	@ResponseBody
	@RequestMapping("/selectList")
	public R selectList(){
		//查询列表数据
		List<SysDeptEntity> list = sysDeptService.queryDeptList();
		
		return R.ok().put("list", list);
	}
	
	
	/**
	 * 选择菜单(添加、修改部门)
	 */
	@ResponseBody
	@RequestMapping("/select")
//	@RequiresPermissions("sysdept:select")
	public R select(){
		//查询列表数据
		List<SysDeptEntity> deptList = sysDeptService.queryDeptList();
		//添加顶级部门
		SysDeptEntity root = new SysDeptEntity();
		root.setDeptId(0);
		root.setName("长安海关");
		root.setParentId(-1);
		root.setOpen(true);
		deptList.add(root);
		return R.ok().put("deptList", deptList);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("sysdept:save")
	public R save(@RequestBody SysDeptEntity sysDept){
		sysDept.setCreateUserId(ShiroUtils.getUserId());//新增的人id，时间

		sysDeptService.save(sysDept);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("sysdept:update")
	public R update(@RequestBody SysDeptEntity sysDept){
		sysDept.setLastUpdateUserId(ShiroUtils.getUserId());//最后修改人的id，
		sysDeptService.update(sysDept);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete")
	@RequiresPermissions("sysdept:delete")
	public R delete(@RequestBody Integer[] deptIds){
		sysDeptService.deleteBatch(deptIds);
		
		return R.ok();
	}
	
	//
	@ResponseBody
	@RequestMapping("/delFile")
	public R delFile(@RequestBody Integer[] deptIds){
		sysDeptService.updateFileNull(deptIds);
		
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
			String path="E:/file/upImg/deptFile/"+fileName;
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
	public R upload(@RequestParam(value="file") MultipartFile  file, @RequestParam(value="deptId") String deptId, 
			@RequestParam(value="name") String name,@RequestParam(value="sysRank") String sysRank,HttpServletRequest request) throws IOException{
		name=java.net.URLDecoder.decode(name,"utf-8");
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
		String path = request.getSession().getServletContext().getRealPath("/upImg/fileload/");
		String filePath = path + "\\" + dateFileName;
		//(1)文件保存
		file.transferTo(new File(filePath));
		//(2)文件信息保存至数据库中:路径、临时文件名
		SysDeptEntity sysDeptEntity = null;
		if(deptId != null && !"".equals(deptId)){//deptId不为空则更新
			//查询
			sysDeptEntity = sysDeptService.queryObject(Integer.valueOf(deptId));
			sysDeptEntity.setName(name);
			//保存到数据库里面的 时间加后缀
			sysDeptEntity.setDataPath(dateFileName);
			//保存到数据库里面的原文件名
			sysDeptEntity.setFileNames(originalFilename);
			//设置修改人
			sysDeptEntity.setLastUpdateUserId(ShiroUtils.getUserId());//最后修改人的id，
			//更新
			sysDeptService.update(sysDeptEntity);
		}else{//deptId为空则新增
			sysDeptEntity = new SysDeptEntity();
			//设置deptId,看是否为自动增长，如果不是自动增长则需赋默认值
			//sysDeptEntity.setDeptId(Integer.valueOf(deptId));
			//设置名称
			sysDeptEntity.setName(name);
			//排序
			sysDeptEntity.setSysRank(sysRank);
			//保存到数据库里面的 时间加后缀
			sysDeptEntity.setDataPath(dateFileName);
			//保存到数据库里面的原文件名
			sysDeptEntity.setFileNames(originalFilename);
			//设置新增人
			sysDeptEntity.setCreateUserId(ShiroUtils.getUserId());//新增的人id，时间
			//保存
			sysDeptService.save(sysDeptEntity);
		}
		R r = R.ok();
		r.put("fileNames",originalFilename);
		r.put("dataPath",dateFileName);
		r.put("deptId", sysDeptEntity.getDeptId());
	    return r;
	}
	
	
	/**
	 * 上传文件  @RequestParam() 必须使用 html name 属性 id不起作用
	 */
	/*@ResponseBody
	@RequestMapping("/upFile") 
	public String upFile(@RequestParam("file") CommonsMultipartFile file,
			HttpServletRequest request,HttpServletResponse response) { // /
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		    String fileName = sdf.format(date);
			String type = file.getOriginalFilename().substring(
					file.getOriginalFilename().lastIndexOf("."));// 取文件格式后缀名
			fileName = fileName + type;// 文件名
			//String path = request.getSession().getServletContext().getRealPath("/upImg/deptFile/" + fileName);// 存放位置
			String path="E:/file/upImg/deptFile/"+fileName;
			File destFile = new File(path);
			try {
				// FileUtils.copyInputStreamToFile()这个方法里对IO进行了自动操作，不需要额外的再去关闭IO流
				FileUtils.copyInputStreamToFile(file.getInputStream(), destFile);// 复制临时文件到指定目录下
				return fileName;
			} catch (IOException e) {
				e.printStackTrace();
			}
		return "err";
	}*/
	
}
