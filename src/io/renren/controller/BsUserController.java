package io.renren.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.stereotype.Controller;

import io.renren.entity.BsUserEntity;
import io.renren.entity.InfoVo;
import io.renren.service.BsUserService;
import io.renren.utils.Messge;
import io.renren.utils.PageUtils;
import io.renren.utils.PhoneFormatCheckUtils;
import io.renren.utils.R;


/**
 * 
 * 
 * @author 
 * @email 
 * @date 2017-03-24 14:04:39
 */
@Controller
@RequestMapping("bsuser")
public class BsUserController {
	@Autowired
	private BsUserService bsUserService;
	
	@RequestMapping("/bsuser.html")
	public String list(){
		return "bsuser/bsuser.html";
	}
	
	
	@RequestMapping("/difiInfo")
	public String difiInfo(HttpServletRequest request) {
		String bsuer_id=request.getParameter("id");
		System.out.println("bsuer_id="+bsuer_id);		
	    request.setAttribute("bsuer_id", bsuer_id);
	    
		//return "sys/sysdifiinfotest.html";
		return "sys/difi/sysdifiinfobuser.jsp";
	}
	
	@RequestMapping("/test")
	public String test(HttpServletRequest request) {
		String bsuer_id=request.getParameter("id");
		System.out.println("bsuer_id="+bsuer_id);		
	    request.setAttribute("bsuer_id", bsuer_id);
		return "sys/t2.jsp";
	}
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("bsuser:list")
	public R list(String mobile,String name,String bs_user_state,Integer page, Integer limit){
		Map<String, Object> map = new HashMap<>();
		map.put("mobile", mobile);
		map.put("name", name);
		map.put("bs_user_state", bs_user_state);
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		
		//查询列表数据
		List<BsUserEntity> bsUserList = bsUserService.queryList(map);
		int total = bsUserService.queryTotal(map);
		
		PageUtils pageUtil = new PageUtils(bsUserList, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{bsUserId}")
	@RequiresPermissions("bsuser:info")
	public R info(@PathVariable("bsUserId") String bsUserId){
		BsUserEntity bsUser = bsUserService.queryObject(bsUserId);
		
		return R.ok().put("bsUser", bsUser);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("bsuser:save")
	public R save(@RequestBody BsUserEntity bsUser){
		bsUserService.save(bsUser);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("bsuser:update")
	public R update(@RequestBody BsUserEntity bsUser){
		System.out.println(bsUser.getApplDate());
		bsUserService.update(bsUser);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete")
	@RequiresPermissions("bsuser:delete")
	public R delete(@RequestBody String[] bsUserIds){
		bsUserService.deleteBatch(bsUserIds);
		
		return R.ok();
	}
	
	/**
	 * 审核通过
	 */
	@ResponseBody
	@RequestMapping("/approved")
	@RequiresPermissions("bsuser:approved")
	public R approved(@RequestBody String[] bsUserIds){
		System.out.println("bsUserIds="+bsUserIds);
		
		bsUserService.approved(bsUserIds); //通过审核
		
		List<BsUserEntity> mobileList=bsUserService.queryMobileList(bsUserIds);  //发送短信号码
		
		for(BsUserEntity user : mobileList){
			String sendNo=null;
			if(PhoneFormatCheckUtils.isChinaPhoneLegal(user.getMobile())){  //手机号码格式是否正确
				//System.out.println("格式正确手机号码="+user.getMobile());
				sendNo=user.getMobile()+";";
			}
			if(sendNo!=null){
				sendNo=sendNo.substring(0, sendNo.length()-1);
				Messge.send("300003", "yangqs", "Nm313191",sendNo,"您提交的信息已经通过审核，现在可以报名参加活动了");  //发送短信
			}
		}
		
		return R.ok();
	}
	
	
	/**
	 * 审核失败
	 */
	@ResponseBody
	@RequestMapping("/auditFailure")
	@RequiresPermissions("bsuser:auditFailure")
	public R auditFailure(@RequestBody String[] bsUserIds){
		bsUserService.auditFailure(bsUserIds);
		
        List<BsUserEntity> mobileList=bsUserService.queryMobileList(bsUserIds);  //发送短信号码
		
		for(BsUserEntity user : mobileList){
			String sendNo=null;
			if(PhoneFormatCheckUtils.isChinaPhoneLegal(user.getMobile())){  //手机号码格式是否正确
				//System.out.println("格式正确手机号码="+user.getMobile());
				sendNo=user.getMobile()+";";
			}
			if(sendNo!=null){
				sendNo=sendNo.substring(0, sendNo.length()-1);
				Messge.send("300003", "yangqs", "Nm313191",sendNo,"您提交的认证信息不符合要求，未能通过审核，不能参加活动报名，若有问题请联系公司工作人员或客服");  //发送短信
			}
		}
		return R.ok();
	}
	
	/**
	 * 解除审核
	 */
	@ResponseBody
	@RequestMapping("/removeAudit")
	@RequiresPermissions("bsuser:removeAudit")
	public R removeAudit(@RequestBody String[] bsUserIds){
		bsUserService.removeAudit(bsUserIds);
		return R.ok();
	}
	
	/**  导出
	 * @throws IOException 
	 * 
	 */
	@RequestMapping(value="/exports",method={RequestMethod.GET,RequestMethod.POST})
	@RequiresPermissions("bsuser:exports")
	public void exports(HttpServletRequest request,HttpServletResponse response) throws IOException {
		OutputStream outputStream = null;  
		//第一步创建一个webbook ,对应一个Excel文件
     //   HSSFWorkbook webBook = new HSSFWorkbook();   //03版本
		XSSFWorkbook webBook = new XSSFWorkbook();      //07至以后版本
        //第二步，在webbook中添 添加一个sheet 对应的Excel 文件中的sheet
        XSSFSheet sheet = webBook.createSheet("Sheet信息");
        //第三步，在sheet中添加表头 第 0 行（从 0 开始的），老版本的poi 对Excel的行数列数有限制 short
        XSSFRow row = sheet.createRow((int)0);
        //第四步，创建单元格，并设置表头居中
//        XSSFCellStyle style = webBook.createCellStyle();
//        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);//创建一个居中格式
        
        XSSFCellStyle headerStyle = webBook.createCellStyle(); //标题样式
		headerStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		headerStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		XSSFFont headerFont = webBook.createFont();	//标题字体
		headerFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		headerFont.setFontHeightInPoints((short)11);
		headerStyle.setFont(headerFont);
		short width = 20,height=25*20;
		sheet.setDefaultColumnWidth(width);
        
        XSSFCell cell = null;
        String[] strs = {"姓名","手机号码","备注","申请日期","状态"};
        for (int i = 0; i < strs.length; i++) {
            cell = row.createCell(i); 
            cell.setCellValue(strs[i]);
            cell.setCellStyle(headerStyle);
        }
        
        //第五步  写入实体数据 实际应用中这些数据从数据库得到
        HashMap map=new HashMap();
        List<BsUserEntity> list = bsUserService.queryAllList(map);
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (int i = 0; i < list.size(); i++) {
        	BsUserEntity stu = new BsUserEntity();
            stu = list.get(i);
            row = sheet.createRow((int) i + 1 );
        //第四步， 创建单元格，并设置值
        row.createCell(0).setCellValue((String)stu.getName());
        row.createCell(1).setCellValue((String)stu.getMobile());
        row.createCell(2).setCellValue((String)stu.getNote());
      //  row.createCell(3).setCellValue((Date)stu.getApplDate());
        row.createCell(3).setCellValue(sdf.format(stu.getApplDate()));
        if("0".equals(stu.getBsUserState())){
        	 row.createCell(4).setCellValue("未审核");
        }
        if("1".equals(stu.getBsUserState())){
       	    row.createCell(4).setCellValue("审核失败");
       }
        if("2".equals(stu.getBsUserState())){
    	   row.createCell(4).setCellValue("审核通过");
       }
       
    }
    	try {
    		String fileName="test.xlsx";
    	    response.setContentType("application/vnd.ms-excel");
    	    response.setHeader("Content-disposition", "attachment;filename="+ URLEncoder.encode(fileName, "utf-8"));
    	    outputStream = response.getOutputStream();
    	    webBook.write(outputStream);  
		} catch (Exception e) {
			 System.out.println("导出异常");
			 e.printStackTrace();
		}finally{
			outputStream.flush();
			outputStream.close();
			webBook.close();
		}
       
	}
	
}
