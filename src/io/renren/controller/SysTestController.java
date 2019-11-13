package io.renren.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.stereotype.Controller;

import io.renren.entity.SysTestEntity;
import io.renren.service.SysTestService;
import io.renren.utils.ObjectExcelRead;
import io.renren.utils.PageUtils;
import io.renren.utils.R;
import io.renren.utils.ShiroUtils;


/**
 * 
 * 
 * @author 
 * @email
 * @date 2017-03-24 15:58:21
 */
@Controller
@RequestMapping("systest")
public class SysTestController {
	@Autowired
	private SysTestService sysTestService;
	
	@RequestMapping("/systest.html")
	public String list(){
		return "systest/systest.html";
	}
	
	@RequestMapping("/systest2")
	public String list2(HttpServletRequest request) throws ServletException, IOException{
		String test_id=request.getParameter("id");
		System.out.println("test_id="+test_id);
		System.out.println("systest2");
		
	    request.setAttribute("test_id", test_id);
	    
		//return "sys/sysdifiinfotest.html";
		return "sys/sysdifiinfotest.jsp";
	   // return "sys/difi/test.jsp";
	}
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("systest:list")
	public R list(Integer page, Integer limit){
		Map<String, Object> map = new HashMap<>();
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		
		//查询列表数据
		List<SysTestEntity> sysTestList = sysTestService.queryList(map);
		int total = sysTestService.queryTotal(map);
		
		PageUtils pageUtil = new PageUtils(sysTestList, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{testId}")
	@RequiresPermissions("systest:info")
	public R info(@PathVariable("testId") Long testId){
		SysTestEntity sysTest = sysTestService.queryObject(testId);
		
		return R.ok().put("sysTest", sysTest);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("systest:save")
	public R save(@RequestBody SysTestEntity sysTest){
		sysTestService.save(sysTest);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("systest:update")
	public R update(@RequestBody SysTestEntity sysTest){
		sysTestService.update(sysTest);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete")
	@RequiresPermissions("systest:delete")
	public R delete(@RequestBody Long[] testIds){
		sysTestService.deleteBatch(testIds);
		
		return R.ok();
	}
	
	/**
	 * 导入
	 */
	@ResponseBody
	@RequestMapping("/imports")
	@RequiresPermissions("systest:imports")
	public R imports(@RequestParam("excle") CommonsMultipartFile file,HttpServletRequest request){     ///
		
		if (!file.isEmpty()) {
		 long  startTime=System.currentTimeMillis();
			Date date = new Date();
			//SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			//String fileName  = sdf.format(date);
			String fileName=ShiroUtils.getUserEntity().getUsername();  //用户名作为文件名
			String type = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));// 取文件格式后缀名
			fileName= fileName + type;//文件名
			String path = request.getSession().getServletContext().getRealPath("/uploadFiles/excle/" + fileName);// 存放位置
			File destFile = new File(path);
			System.out.println(path);
			try {
				// FileUtils.copyInputStreamToFile()这个方法里对IO进行了自动操作，不需要额外的再去关闭IO流
				FileUtils.copyInputStreamToFile(file.getInputStream(), destFile);// 复制临时文件到指定目录下
				String address=request.getSession().getServletContext().getRealPath("/uploadFiles/excle");
				long  endTime=System.currentTimeMillis();
				//System.out.println("address="+address);
				List<HashMap> listHm=null;
				if(".xls".equals(type)){
					 listHm = (List)ObjectExcelRead.readExcelXls(address, fileName, 1, 0, 0);	  //读取excle数据  03或以前版本
				}
				else{
					listHm = (List)ObjectExcelRead.readExcelXlsx(address, fileName, 1, 0, 0);	  //读取excle数据  07以后版本
				}
			
				System.out.println("listHm="+listHm.size());
				if(listHm==null||listHm.size()==0){
					return R.error("系统读取excle数据为0，请检查excel是否有数据");
				}else{
					SysTestEntity test=new SysTestEntity();
					SimpleDateFormat sdf =  new SimpleDateFormat("yyyyMMddHHmmss");
					for(int i=0;i<listHm.size();i++){
						HashMap hm=listHm.get(i);
						test.setName(hm.get("var0").toString());
						String dates=hm.get("var1").toString();
						try {
							test.setStartDate(sdf.parse(dates));
						} catch (Exception e) {
							// TODO: handle exception
							test.setStartDate(null);
						}
						System.out.println("时间="+hm.get("var1").toString());
						sysTestService.save(test);  
					}
					System.out.println(" 运行时间："+String.valueOf(endTime-startTime)+"ms");
					return R.ok();
				}
			} catch (IOException e) {
			    e.printStackTrace();
			}
			return R.error("系统读取不到excle,请重新导入");
		}
		
	/*	
		System.out.println("-------");
        long  startTime=System.currentTimeMillis();
        System.out.println("fileName："+file.getOriginalFilename());
        String path="E:/"+new Date().getTime()+file.getOriginalFilename();  
        System.out.println("path="+path);
         
        File newFile=new File(path);
        //通过CommonsMultipartFile的方法直接写文件（注意这个时候）
        file.transferTo(newFile);
        long  endTime=System.currentTimeMillis();
        System.out.println("方法二的运行时间："+String.valueOf(endTime-startTime)+"ms");*/
		
		return R.ok();
	}
	
}
