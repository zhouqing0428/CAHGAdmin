package io.renren.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.alibaba.druid.util.StringUtils;

import io.renren.entity.CahgAddressBookEntity;
import io.renren.entity.SysDeptEntity;
import io.renren.service.CahgAddressBookService;
import io.renren.service.SysDeptService;
import io.renren.utils.ObjectExcelRead2;
import io.renren.utils.PageUtils;
import io.renren.utils.R;
import io.renren.utils.ShiroUtils;

/**
 * 
 * 
 * @author 
 * @email 
 * @date 2017-07-14 17:51:32
 */
@Controller
@RequestMapping("cahgaddressbook")
public class CahgAddressBookController {
	@Autowired
	private CahgAddressBookService cahgAddressBookService;
	@Autowired
	private SysDeptService sysDeptService;
	
	@RequestMapping("/cahgaddressbook.html")
	public String list(){
		return "cahgaddressbook/cahgaddressbook.html";
	}
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("cahgaddressbook:list")
	public R list(String deptId,String name,Integer page, Integer limit){
		Map<String, Object> map = new HashMap<>();
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		map.put("deptId", deptId);
		map.put("name", name);
		map.put("dept_id", ShiroUtils.getDeptId());
		
		//查询列表数据
		List<CahgAddressBookEntity> cahgAddressBookList = cahgAddressBookService.queryList(map);
		int total = cahgAddressBookService.queryTotal(map);
		
		PageUtils pageUtil = new PageUtils(cahgAddressBookList, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{addressLookId}")
	@RequiresPermissions("cahgaddressbook:info")
	public R info(@PathVariable("addressLookId") Integer addressLookId){
		CahgAddressBookEntity cahgAddressBook = cahgAddressBookService.queryObject(addressLookId);
		
		return R.ok().put("cahgAddressBook", cahgAddressBook);
	}
	
	/**
	 * 导出
	 * @throws IOException 
	 */
	@RequestMapping(value="/exports",method={RequestMethod.GET,RequestMethod.POST})
	@RequiresPermissions("cahgaddressbook:exports")
	public void exports(String deptId, String name, HttpServletResponse response) throws IOException {
		OutputStream outputStream = null;
		// 第一步创建一个webbook ,对应一个Excel文件
		// HSSFWorkbook webBook = new HSSFWorkbook(); //03版本
		XSSFWorkbook webBook = new XSSFWorkbook(); // 07至以后版本
		// 第二步，在webbook中添 添加一个sheet 对应的Excel 文件中的sheet
		XSSFSheet sheet = webBook.createSheet("Sheet信息");
		// 第三步，在sheet中添加表头 第 0 行（从 0 开始的），老版本的poi 对Excel的行数列数有限制 short
		XSSFRow row = sheet.createRow((int) 0);
		// 第四步，创建单元格，并设置表头居中
		// XSSFCellStyle style = webBook.createCellStyle();
		// style.setAlignment(HSSFCellStyle.ALIGN_CENTER);//创建一个居中格式

		XSSFCellStyle headerStyle = webBook.createCellStyle(); // 标题样式
		headerStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		headerStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		XSSFFont headerFont = webBook.createFont(); // 标题字体
		headerFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		headerFont.setFontHeightInPoints((short) 11);
		headerStyle.setFont(headerFont);
		short width = 20, height = 25 * 20;
		sheet.setDefaultColumnWidth(width);

		XSSFCell cell = null;
		String[] strs = { "姓名", "手机号码", "办公内线", "办公外线", "工号", "职务", "科室" };
		for (int i = 0; i < strs.length; i++) {
			cell = row.createCell(i);
			cell.setCellValue(strs[i]);
			cell.setCellStyle(headerStyle);
		}

		// 第五步 写入实体数据 实际应用中这些数据从数据库得到
		Map<String, Object> map = new HashMap<>();
		map.put("deptId", deptId);
		map.put("name", name);
		List<CahgAddressBookEntity> list = cahgAddressBookService.queryList(map);
		for (int i = 0; i < list.size(); i++) {
			CahgAddressBookEntity adressBook = new CahgAddressBookEntity();
			adressBook = list.get(i);
			row = sheet.createRow((int) i + 1);
			// 第四步， 创建单元格，并设置值
			row.createCell(0).setCellValue((String) adressBook.getName());
			row.createCell(1).setCellValue((String) adressBook.getMobile());
			row.createCell(2).setCellValue((String) adressBook.getInterior());
			row.createCell(3).setCellValue((String) adressBook.getExternal());
			row.createCell(4).setCellValue((String) adressBook.getJobNumber());
			row.createCell(5).setCellValue((String) adressBook.getDuty());
			row.createCell(6).setCellValue((String) adressBook.getDeptName());
		}
		try {
			String fileName = "通讯录.xlsx";
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, "utf-8"));
			outputStream = response.getOutputStream();
			webBook.write(outputStream);
		} catch (Exception e) {
			System.out.println("导出异常");
			e.printStackTrace();
		} finally {
			outputStream.flush();
			outputStream.close();
			webBook.close();
		}
	}
	
	@ResponseBody
	@RequestMapping("/imports")
	@RequiresPermissions("cahgaddressbook:imports")
	public R imports(@RequestParam("excle") CommonsMultipartFile file, HttpServletRequest request) {
		if (!file.isEmpty()) {
			long startTime = System.currentTimeMillis();
			String fileName = "book" + ShiroUtils.getUserEntity().getUsername(); // 文件名
			String type = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));// 取文件格式后缀名
			fileName = fileName + type;// 文件名
			String path = request.getSession().getServletContext().getRealPath("/upImg/excle/" + fileName);// 存放位置
			File destFile = new File(path);
			try {
				// FileUtils.copyInputStreamToFile()这个方法里对IO进行了自动操作，不需要额外的再去关闭IO流
				FileUtils.copyInputStreamToFile(file.getInputStream(), destFile);// 复制临时文件到指定目录下
				String address = request.getSession().getServletContext().getRealPath("/upImg/excle");
				long endTime = System.currentTimeMillis();
				List<Map<String, Object>> listHm = null;
				if (".xls".equals(type)) {
					listHm = (List) ObjectExcelRead2.readExcelXls(address, fileName, 1, 0, 0); // 读取excle数据03或以前版本
				} else {
					listHm = (List) ObjectExcelRead2.readExcelXlsx(address, fileName, 1, 0, 0); // 读取excle数据07以后版本
				}

				int size = listHm.size();
				if (listHm == null || size == 0) {
					return R.error("系统读取excle数据为0，请检查excel是否有数据");
				} else {
					int success = 0;
					Map<String, Object> hm = null;
					Set<String> numberSet = new HashSet<>();
					for (int i = 0; i < size; i++) {
						hm = listHm.get(i);
						if (hm.get("var0") == null || StringUtils.isEmpty(hm.get("var0").toString())) {
							return R.error("第" + (i + 1) + "行姓名不能为空");
						}
						if (hm.get("var6") == null || StringUtils.isEmpty(hm.get("var6").toString())) {
							return R.error("第" + (i + 1) + "行科室编码不能为空");
						}
						numberSet.add(hm.get("var6").toString());
					}
					List<String> numberList = new ArrayList<String>();
					for (String number : numberSet) {
						numberList.add(number);
					}
					//根据科室编码批量取出科室信息
					List<SysDeptEntity> deptList = sysDeptService.queryListByNumbers(numberList);
					//根据科室编码缓存科室信息
					Map<String, SysDeptEntity> deptMap = mapDeptInfo(deptList);
					
					SysDeptEntity dept = null;
					List<CahgAddressBookEntity> addList = new ArrayList<>();
					for (int i = 0; i < size; i++) {
						CahgAddressBookEntity book = new CahgAddressBookEntity();
						hm = listHm.get(i);
						book.setName(hm.get("var0").toString()); // 姓名
						book.setMobile(hm.get("var1").toString()); // 手机号码
						book.setInterior(hm.get("var2").toString()); // 内线
						book.setExternal(hm.get("var3").toString()); // 外线
						book.setJobNumber(hm.get("var4").toString()); // 工号
						book.setDuty(hm.get("var5").toString()); // 职务
						String deptNumber = hm.get("var6").toString().trim();
						if (!StringUtils.isEmpty(deptNumber) && deptMap !=null && deptMap.containsKey(deptNumber)) {
							dept = deptMap.get(deptNumber);
							if (dept != null) { // 有此科室
								book.setDeptId(dept.getDeptId());
								addList.add(book);
								success++;
							}
						}
					}
					cahgAddressBookService.batchSave(addList);
					
					System.out.println(" 运行时间：" + String.valueOf(endTime - startTime) + "ms");
					return R.ok().put("msg", "操作成功，科室匹配错误数量:" + (size - success));
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			return R.error("系统读取不到excle,请重新导入");
		}
		return R.ok();
	}
	
	/**
	 * 根据科室编码缓存科室信息
	 * @param deptList 科室集合
	 * @return
	 */
	private Map<String, SysDeptEntity> mapDeptInfo(List<SysDeptEntity> deptList) {
		if (deptList == null || deptList.size() == 0) {
			return null;
		}

		Map<String, SysDeptEntity> map = new HashMap<>();
		for (SysDeptEntity deptInfo : deptList) {
			map.put(deptInfo.getNumber(), deptInfo);
		}

		return map;
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("cahgaddressbook:save")
	public R save(@RequestBody CahgAddressBookEntity cahgAddressBook){
		cahgAddressBook.setCreateUserId(ShiroUtils.getUserId());
		cahgAddressBookService.save(cahgAddressBook);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("cahgaddressbook:update")
	public R update(@RequestBody CahgAddressBookEntity cahgAddressBook){
		cahgAddressBook.setLastUpdateUserId(ShiroUtils.getUserId());
//		cahgAddressBook.setDeptId(ShiroUtils.getUserEntity().getDeptId());
		cahgAddressBookService.update(cahgAddressBook);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete")
	@RequiresPermissions("cahgaddressbook:delete")
	public R delete(@RequestBody Integer[] addressLookIds){
		cahgAddressBookService.deleteBatch(addressLookIds);
		
		return R.ok();
	}
	
	/**
     * 模板下载
     * @param request
     * @param response
     */
    @RequestMapping(value = "/downloadTemplate")
    @RequiresPermissions("cahgaddressbook:imports")
    public void downloadTemplate(HttpServletRequest request, HttpServletResponse response) {
        InputStream stream = getClass().getClassLoader().getResourceAsStream("excelTemplate/address_book.xlsx");
        String fileName = "通讯录导入模板.xlsx";

        BufferedInputStream br = null;
        OutputStream out = null;
        try {
            br = new BufferedInputStream(stream);
            byte[] buf = new byte[1024];
            int len = 0;

            response.reset();

            fileName = new String(fileName.getBytes("gb2312"), "ISO8859-1");
            response.setContentType("application/vnd.ms-excel;charset=utf-8");
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
            out = response.getOutputStream();
            while ((len = br.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            br.close();
            out.flush();
            out.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
