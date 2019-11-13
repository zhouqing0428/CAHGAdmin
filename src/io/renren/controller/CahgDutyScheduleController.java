package io.renren.controller;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.stereotype.Controller;

import io.renren.entity.CahgAddressBookEntity;
import io.renren.entity.CahgDutyScheduleEntity;
import io.renren.entity.SysTestEntity;
import io.renren.service.CahgDutyScheduleService;
import io.renren.utils.ObjectExcelRead;
import io.renren.utils.PageUtils;
import io.renren.utils.R;
import io.renren.utils.ShiroUtils;


/**
 * 值班安排
 * 
 * @author 
 * @email 
 * @date 2017-07-25 16:47:18
 */
@Controller
@RequestMapping("cahgdutyschedule")
public class CahgDutyScheduleController {
	@Autowired
	private CahgDutyScheduleService cahgDutyScheduleService;
	
	@RequestMapping("/cahgdutyschedule.html")
	public String list(){
		return "cahgdutyschedule/cahgdutyschedule.html";
	}
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("cahgdutyschedule:list")
	public R list(String mobile,String name,String category,String status,String startDate,String endDate,Integer page, Integer limit){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		Date start_date=null;
		Date end_date=null;
		if(startDate!=null&&!startDate.isEmpty()){
			try {
				start_date=sdf.parse(startDate);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(endDate!=null&&!endDate.isEmpty()){
			try {
				end_date=sdf.parse(endDate);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Map<String, Object> map = new HashMap<>();
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		map.put("mobile", mobile);
		map.put("name", name);
		map.put("category", category);
		map.put("status", status);
		map.put("start_date", start_date);
		map.put("end_date", end_date);
		
		//查询列表数据
		List<CahgDutyScheduleEntity> cahgDutyScheduleList = cahgDutyScheduleService.queryList(map);
		int total = cahgDutyScheduleService.queryTotal(map);
		
		PageUtils pageUtil = new PageUtils(cahgDutyScheduleList, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{dutyScheduleId}")
	@RequiresPermissions("cahgdutyschedule:info")
	public R info(@PathVariable("dutyScheduleId") Integer dutyScheduleId){
		CahgDutyScheduleEntity cahgDutySchedule = cahgDutyScheduleService.queryObject(dutyScheduleId);
		
		return R.ok().put("cahgDutySchedule", cahgDutySchedule);
	}
	
	
	/**
	 * 导出
	 * @throws IOException 
	 */
	@RequestMapping(value="/exports",method={RequestMethod.GET,RequestMethod.POST})
	@RequiresPermissions("cahgdutyschedule:exports")
	public void exports(String mobile,String name,String category,String status,HttpServletResponse response) throws IOException{
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
		short width = 35,height=25*20;  //宽度,高度
		sheet.setDefaultColumnWidth(width);
        
        XSSFCell cell = null;
        String[] strs = {"姓名","手机号码","办公内线","办公外线","值班时间","备注","类别"};
        for (int i = 0; i < strs.length; i++) {
            cell = row.createCell(i); 
            cell.setCellValue(strs[i]);
            cell.setCellStyle(headerStyle);
        }
        
        //第五步  写入实体数据 实际应用中这些数据从数据库得到
        HashMap<String, Object> map=new HashMap();
        map.put("deptId", mobile);
		map.put("name", name);
	    map.put("category", category);
		map.put("status", status);
        List<CahgDutyScheduleEntity> list = cahgDutyScheduleService.queryList(map);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (int i = 0; i < list.size(); i++) {
        	CahgDutyScheduleEntity schedule = new CahgDutyScheduleEntity();
        	schedule = list.get(i);
            row = sheet.createRow((int) i + 1 );
        //第四步， 创建单元格，并设置值
        row.createCell(0).setCellValue((String)schedule.getName());
        row.createCell(1).setCellValue((String)schedule.getMobile());
        row.createCell(2).setCellValue((String)schedule.getInterior());
        row.createCell(3).setCellValue((String)schedule.getExternal());
        row.createCell(4).setCellValue(sdf.format((Date)schedule.getWorkTime()));
        row.createCell(5).setCellValue((String)schedule.getRemark());
        if(schedule.getCategory()==0){
        	 row.createCell(6).setCellValue("一般干部");
        }else {
        	 row.createCell(6).setCellValue("领导");
		}
       
    }
    	try {
    		String fileName="值班安排.xlsx";
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
	
	/**
	 * 导入
	 */
	@ResponseBody
	@RequestMapping("/imports")
	@RequiresPermissions("cahgdutyschedule:imports")
	public R imports(@RequestParam("excle") CommonsMultipartFile file,HttpServletRequest request){     ///
		if (!file.isEmpty()) {
			 int category=0; //默认为一般干部
		     long  startTime=System.currentTimeMillis();
			//Date date = new Date();
			//SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			//String fileName  = sdf.format(date);
		    System.out.println("file.getOriginalFilename()="+file.getOriginalFilename());
			//String fileName=ShiroUtils.getUserEntity().getUsername();  //用户名作为文件名
			String fileName= file.getOriginalFilename();//文件名
			String type = file.getOriginalFilename().substring(fileName.lastIndexOf("."));// 取文件格式后缀名
		    if(fileName.contains("领导")){
		    	category=1;
		    }
			String path = request.getSession().getServletContext().getRealPath("/uploadFiles/excle/" +fileName);// 存放位置
			File destFile = new File(path);
			System.out.println(path);
			try {
				// FileUtils.copyInputStreamToFile()这个方法里对IO进行了自动操作，不需要额外的再去关闭IO流
				FileUtils.copyInputStreamToFile(file.getInputStream(), destFile);// 复制临时文件到指定目录下
				String address=request.getSession().getServletContext().getRealPath("/uploadFiles/excle");
				long  endTime=System.currentTimeMillis();
				List<HashMap> listHm=null;
				if(".xls".equals(type)){
					 listHm = (List)ObjectExcelRead.readExcelXls(address, fileName, 4, 0, 0);	  //读取excle数据  03或以前版本
				}
				else{
					listHm = (List)ObjectExcelRead.readExcelXlsx(address, fileName, 4, 0, 0);	  //读取excle数据  07以后版本
				}
			
				System.out.println("listHm="+listHm.size());
				if(listHm==null||listHm.size()==0){
					return R.error("系统读取excle数据为0，请检查excel是否有数据");
				}else{
					SimpleDateFormat sdf =  new SimpleDateFormat("yyyyMMddHHmmss");
					for(int i=0;i<listHm.size();i++){
						CahgDutyScheduleEntity schedule=new CahgDutyScheduleEntity();
						HashMap hm=listHm.get(i);
						schedule.setName(hm.get("var2").toString());
						schedule.setExternal(hm.get("var4").toString());
						schedule.setInterior(hm.get("var5").toString());
						schedule.setMobile(hm.get("var6").toString());
						schedule.setStatus(0);  //默认显示
						schedule.setCategory(category);
						try {
							schedule.setWorkTime(sdf.parse(hm.get("var0").toString()));
						} catch (Exception e) {
							// TODO: handle exception
							schedule.setWorkTime(null);
						}
						cahgDutyScheduleService.save(schedule);  
					}
					System.out.println(" 运行时间："+String.valueOf(endTime-startTime)+"ms");
					return R.ok();
				}
			} catch (IOException e) {
			    e.printStackTrace();
			}
			return R.error("系统读取不到excle,请重新导入");
		}
		return R.error("导入异常,请检查数据格式是否符合要求");
	}
	
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("cahgdutyschedule:save")
	public R save(@RequestBody CahgDutyScheduleEntity cahgDutySchedule){
		cahgDutySchedule.setCreateUserId(ShiroUtils.getUserId());
		cahgDutyScheduleService.save(cahgDutySchedule);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("cahgdutyschedule:update")
	public R update(@RequestBody CahgDutyScheduleEntity cahgDutySchedule){
		cahgDutySchedule.setLastUpdateUserId(ShiroUtils.getUserId());
		cahgDutyScheduleService.update(cahgDutySchedule);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete")
	@RequiresPermissions("cahgdutyschedule:delete")
	public R delete(@RequestBody Integer[] dutyScheduleIds){
		cahgDutyScheduleService.deleteBatch(dutyScheduleIds);
		
		return R.ok();
	}
	
}
