package io.renren.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author 
 * @email 
 * @date 2017-12-08 17:26:39
 */
public class CahgOfficeWorkEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer officeWorkId;
	//
	private String title;
	//
	private String remark;
	//
	private String fileName;
	//
	private String fileOldName;
	//
	private Integer officeWorkCategoryId;
	//
	private Date createDate;
	//
	private Long createUserId;
	//
	private Date lastUpdateDate;
	//
	private Long lastUpdateUserId;
	//类别
	private String categoryName;
	private String createUserName;
	private String lastUpdateName;

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
	/**
	 * 设置：
	 */
	public void setOfficeWorkId(Integer officeWorkId) {
		this.officeWorkId = officeWorkId;
	}
	/**
	 * 获取：
	 */
	public Integer getOfficeWorkId() {
		return officeWorkId;
	}
	/**
	 * 设置：
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * 获取：
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * 设置：
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * 获取：
	 */
	public String getRemark() {
		return remark;
	}
	/**
	 * 设置：
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	/**
	 * 获取：
	 */
	public String getFileName() {
		return fileName;
	}
	/**
	 * 设置：
	 */
	public void setFileOldName(String fileOldName) {
		this.fileOldName = fileOldName;
	}
	/**
	 * 获取：
	 */
	public String getFileOldName() {
		return fileOldName;
	}
	/**
	 * 设置：
	 */
	public void setOfficeWorkCategoryId(Integer officeWorkCategoryId) {
		this.officeWorkCategoryId = officeWorkCategoryId;
	}
	/**
	 * 获取：
	 */
	public Integer getOfficeWorkCategoryId() {
		return officeWorkCategoryId;
	}
	/**
	 * 设置：
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	/**
	 * 获取：
	 */
	public Date getCreateDate() {
		return createDate;
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
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
}
