package io.renren.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author 
 * @email 
 * @date 2017-06-23 16:05:45
 */
public class SysDeptEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer deptId;
	//上级部门id
	private Integer parentId;
	/**
	 * 上级部门名称
	 */
	private String parentName;
	
	//科室编码
	private String number;
	
	//科室名称
	private String name;
	//添加人
	private Long createUserId;
	//添加时间
	private Date createDate;
	//最后修改人
	private Long lastUpdateUserId;
	//最后修改时间
	private Date lastUpdateDate;
	//科室职责
	private String duty;
	//科室制度
	private String regime;
	//操作规范
	private String workStandard;
	//备注
	private String remark;
	//岗位
	private String role;
	//
	private String fileName;
	//
	private String fileOldName;
	//排序
	private String sysRank;
	//通訊錄
	private String sysContent;
	//保存上传文件的另外名称
	private String dataPath;
	//保存上传文件的原名
	private String fileNames;
	
	//状态：0:显示 1:隐藏
	private Integer status;
	
	private String lastUpdateName;
	private String createUserName;
	
	public String getCreateUserName() {
		return createUserName;
	}
	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}
	public String getLastUpdateName() {
		return lastUpdateName;
	}
	public void setLastUpdateName(String lastUpdateName) {
		this.lastUpdateName = lastUpdateName;
	}
	public String getDataPath() {
		return dataPath;
	}
	public void setDataPath(String dataPath) {
		this.dataPath = dataPath;
	}
	public String getFileNames() {
		return fileNames;
	}
	public void setFileNames(String fileNames) {
		this.fileNames = fileNames;
	}
	public String getSysContent() {
		return sysContent;
	}
	public void setSysContent(String sysContent) {
		this.sysContent = sysContent;
	}
	public String getSysRank() {
		return sysRank;
	}
	public void setSysRank(String sysRank) {
		this.sysRank = sysRank;
	}
	/**
	 * 设置：添加人
	 */
	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
	}
	/**
	 * 获取：添加人
	 */
	public Long getCreateUserId() {
		return createUserId;
	}
	/**
	 * 设置：添加时间
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	/**
	 * 获取：添加时间
	 */
	public Date getCreateDate() {
		return createDate;
	}
	/**
	 * 设置：最后修改人
	 */
	public void setLastUpdateUserId(Long lastUpdateUserId) {
		this.lastUpdateUserId = lastUpdateUserId;
	}
	/**
	 * 获取：最后修改人
	 */
	public Long getLastUpdateUserId() {
		return lastUpdateUserId;
	}
	/**
	 * 设置：最后修改时间
	 */
	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}
	/**
	 * 获取：最后修改时间
	 */
	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}
	/**
	 * ztree属性
	 */
	private Boolean open;
	
	/**
	 * 设置：
	 */
	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}
	/**
	 * 获取：
	 */
	public Integer getDeptId() {
		return deptId;
	}
	/**
	 * 设置：
	 */
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	/**
	 * 获取：
	 */
	public Integer getParentId() {
		return parentId;
	}
	/**
	 * 设置：科室名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：科室名称
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：科室职责
	 */
	public void setDuty(String duty) {
		this.duty = duty;
	}
	/**
	 * 获取：科室职责
	 */
	public String getDuty() {
		if(duty==null){
			duty=" ";
		}
		return duty;
	}
	/**
	 * 设置：科室制度
	 */
	public void setRegime(String regime) {
		if(regime==null){
			regime=" ";
		}
		this.regime = regime;
	}
	/**
	 * 获取：科室制度
	 */
	public String getRegime() {
		if(regime==null){
			regime=" ";
		}
		return regime;
	}
	/**
	 * 设置：操作规范
	 */
	public void setWorkStandard(String workStandard) {
		if(workStandard==null){
			workStandard=" ";
		}
		this.workStandard = workStandard;
	}
	/**
	 * 获取：操作规范
	 */
	public String getWorkStandard() {
		if(workStandard==null){
			workStandard=" ";
		}
		return workStandard;
	}
	/**
	 * 设置：备注
	 */
	public void setRemark(String remark) {
		if(workStandard==null){
			workStandard=" ";
		}
		this.remark = remark;
	}
	/**
	 * 获取：备注
	 */
	public String getRemark() {
		return remark;
	}
	
	public String getParentName() {
		return parentName;
	}
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	

	public Boolean getOpen() {
		return open;
	}
	public void setOpen(Boolean open) {
		this.open = open;
	}

	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFileOldName() {
		return fileOldName;
	}
	public void setFileOldName(String fileOldName) {
		this.fileOldName = fileOldName;
	}
	public String getRole() {
		if(role==null){
			role=" ";
		}
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
}
