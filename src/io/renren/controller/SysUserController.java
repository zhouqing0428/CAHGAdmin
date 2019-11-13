package io.renren.controller;

import io.renren.entity.SysActivityEntity;
import io.renren.entity.SysUserEntity;
import io.renren.service.SysUserRoleService;
import io.renren.service.SysUserService;
import io.renren.utils.PageUtils;
import io.renren.utils.R;
import io.renren.utils.ShiroUtils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * 系统用户
 * 
 * @author 
 * @email 
 * @date 
 */
@RestController
@RequestMapping("/sys/user")
public class SysUserController extends AbstractController {
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private SysUserRoleService sysUserRoleService;
	
	/**
	 * 所有用户列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("sys:user:list")
	public R list(String username, Integer page, Integer limit){
		Map<String, Object> map = new HashMap<>();
		map.put("username", username);
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		
		//查询列表数据
		List<SysUserEntity> userList = sysUserService.queryList(map);
		int total = sysUserService.queryTotal(map);
		
		PageUtils pageUtil = new PageUtils(userList, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	
	/**
	 * 获取登录的用户信息
	 */
	@RequestMapping("/info")
	public R info(){
		return R.ok().put("user", getUser());
	}
	
	/*
	 * 
	 */
	@ResponseBody
	@RequestMapping("/selectList")
	public List<SysUserEntity> selectList(@RequestBody SysUserEntity user){
		Map<String, Object> map = new HashMap<>();
		map.put("deptId", user.getDeptId());
		List<SysUserEntity> userList=sysUserService.selectList(map);//
		
		return userList;
	}
	
	/**
	 * 修改登录用户密码
	 */
	@RequestMapping("/password")
	public R password(String password, String newPassword){
		if(StringUtils.isBlank(newPassword)){
			return R.error("新密码不为能空");
		}
		
		//sha256加密
		password = new Sha256Hash(password).toHex();
		//sha256加密
		newPassword = new Sha256Hash(newPassword).toHex();
				
		//更新密码
		int count = sysUserService.updatePassword(getUserId(), password, newPassword);
		if(count == 0){
			return R.error("原密码不正确");
		}
		
		//退出
		ShiroUtils.logout();
		
		return R.ok();
	}
	
	/**
	 * 用户信息
	 */
	@RequestMapping("/info/{userId}")
	@RequiresPermissions("sys:user:info")
	public R info(@PathVariable("userId") Long userId){
		SysUserEntity user = sysUserService.queryObject(userId);
		
		//获取用户所属的角色列表
		List<Long> roleIdList = sysUserRoleService.queryRoleIdList(userId);
		user.setRoleIdList(roleIdList);
		
		return R.ok().put("user", user);
	}
	
	/**
	 * 保存用户
	 */
	@RequestMapping("/save")
	@RequiresPermissions("sys:user:save")
	public R save(@RequestBody SysUserEntity user){
		user.setCreateUserId(ShiroUtils.getUserId());

		if(StringUtils.isBlank(user.getUsername())){
			return R.error("用户名不能为空");
		}
		if(StringUtils.isBlank(user.getPassword())){
			return R.error("密码不能为空");
		}
		user.setIsLetterLeader(0);
		sysUserService.save(user);
		
		return R.ok();
	}
	
	/**
	 * 修改用户
	 */
	@RequestMapping("/update")
	@RequiresPermissions("sys:user:update")
	public R update(@RequestBody SysUserEntity user){
		user.setLastUpdateUserId(ShiroUtils.getUserId());

		if(StringUtils.isBlank(user.getUsername())){
			return R.error("用户名不能为空");
		}
		
		sysUserService.update(user);
		
		return R.ok();
	}
	
	/**
	 * 设置为信箱主任
	 */
	@RequestMapping("/setLetterLeader/{userId}")
	@RequiresPermissions("sys:user:setLetterLeader")
	public R setLetterLeader(@PathVariable("userId") Long userId){
		int total = sysUserService.queryLetterLeaderCount();
		if(total>=8){
			return R.error("信箱主任人数最不能超过8人");
		}
		Map<String, Object> map=new HashMap<>();
		map.put("userId", userId);
		map.put("isLetterLeader", 1);
		sysUserService.updateLetterLeader(map);
		return R.ok();
	}
	/**
	 * 撤销信箱主任
	 */
	@RequestMapping("/canselLetterLeader")
	@RequiresPermissions("sys:user:delete")
	public R canselLetterLeader(@RequestBody Long[] userIds){
		sysUserService.canselLetterLeader(userIds);
		
		return R.ok();
	}
	/**
	 * 删除用户
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("sys:user:delete")
	public R delete(@RequestBody Long[] userIds){
		if(ArrayUtils.contains(userIds, 1L)){
			return R.error("系统管理员不能删除");
		}
		
		if(ArrayUtils.contains(userIds, getUserId())){
			return R.error("当前用户不能删除");
		}
		
		sysUserService.deleteBatch(userIds);
		sysUserService.deleteUserRole(userIds);
		
		return R.ok();
	}
	
	/**
	 * 上传图片  @RequestParam() 必须使用 html name 属性 id不起作用
	 */
	@ResponseBody
	@RequestMapping("/upImg")
	public String upImg(@RequestParam("photoFile") CommonsMultipartFile file,
			HttpServletRequest request,HttpServletResponse response) { // /
		if (!file.isEmpty()) {
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		    String fileName = sdf.format(date);
			String type = file.getOriginalFilename().substring(
					file.getOriginalFilename().lastIndexOf("."));// 取文件格式后缀名
			fileName = fileName + type;// 文件名
			//String path = request.getSession().getServletContext().getRealPath("/upImg/userPhoto/" + fileName);// 存放位置
			String path="E:/file/upImg/userPhoto/"+fileName;
			File destFile = new File(path);
			try {
				// FileUtils.copyInputStreamToFile()这个方法里对IO进行了自动操作，不需要额外的再去关闭IO流
				FileUtils.copyInputStreamToFile(file.getInputStream(), destFile);// 复制临时文件到指定目录下
				return fileName;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return "err";
	}
	
}
