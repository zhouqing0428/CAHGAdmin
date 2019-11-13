package io.renren.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 常用表格下载
 * 
 * @author 
 * @email 
 * @date 2017-07-24 17:08:54
 */
public class CahgCommonFormsEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer commonFormsId;
	//文件名称
	private String fileName;
	//文件原名
	private String fileOldName;
	//排序号
	private String rank;
	//状态： 0 显示，1 隐藏
	private Integer status;
	//
	private Long createUserId;
	//上传时间
	private Date createDate;
	//
	private Long lastUpdateUserId;
	//
	private Date lastUpdateDate;
	//科室id
	private Integer deptId;
	
	//分类 : 0财务、1后勤、2技术、3机要 4科室日常办公
	private Integer type;
	//科室名称
	private String deptName;

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
	private String lastUpdateName;
	/**
	 * 设置：
	 */
	public void setCommonFormsId(Integer commonFormsId) {
		this.commonFormsId = commonFormsId;
	}
	/**
	 * 获取：
	 */
	public Integer getCommonFormsId() {
		return commonFormsId;
	}
	/**
	 * 设置：文件名称
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	/**
	 * 获取：文件名称
	 */
	public String getFileName() {
		return fileName;
	}
	/**
	 * 设置：文件原名
	 */
	public void setFileOldName(String fileOldName) {
		this.fileOldName = fileOldName;
	}
	/**
	 * 获取：文件原名
	 */
	public String getFileOldName() {
		return fileOldName;
	}
	/**
	 * 设置：排序号
	 */
	public void setRank(String rank) {
		this.rank = rank;
	}
	/**
	 * 获取：排序号
	 */
	public String getRank() {
		return rank;
	}
	/**
	 * 设置：状态： 0 显示，1 隐藏
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：状态： 0 显示，1 隐藏
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * 设置：
	 */
	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
	}
	/**
	 * 获取：
	 */
	public Long getCreateUserId() {
		return createUserId;
	}
	/**
	 * 设置：上传时间
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	/**
	 * 获取：上传时间
	 */
	public Date getCreateDate() {
		return createDate;
	}
	/**
	 * 设置：
	 */
	public void setLastUpdateUserId(Long lastUpdateUserId) {
		this.lastUpdateUserId = lastUpdateUserId;
	}
	/**
	 * 获取：
	 */
	public Long getLastUpdateUserId() {
		return lastUpdateUserId;
	}
	/**
	 * 设置：
	 */
	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}
	/**
	 * 获取：
	 */
	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}
	/**
	 * 设置：科室id
	 */
	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}
	/**
	 * 获取：科室id
	 */
	public Integer getDeptId() {
		return deptId;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
}
