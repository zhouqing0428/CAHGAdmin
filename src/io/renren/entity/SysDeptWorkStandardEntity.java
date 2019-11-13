package io.renren.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author 
 * @email 
 * @date 2017-12-14 14:58:24
 */
public class SysDeptWorkStandardEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer workStandardId;
	//标题
	private String title;
	//
	private String fileName;
	//附件名称
	private String fileOldName;
	//
	private Date createDate;
	//
	private Long createUserId;
	//
	private Date lastUpdateDate;
	//
	private Long lasteUpdateUserId;
	//
	private Integer deptId;

	/**
	 * 设置：
	 */
	public void setWorkStandardId(Integer workStandardId) {
		this.workStandardId = workStandardId;
	}
	/**
	 * 获取：
	 */
	public Integer getWorkStandardId() {
		return workStandardId;
	}
	/**
	 * 设置：标题
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * 获取：标题
	 */
	public String getTitle() {
		return title;
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
	 * 设置：附件名称
	 */
	public void setFileOldName(String fileOldName) {
		this.fileOldName = fileOldName;
	}
	/**
	 * 获取：附件名称
	 */
	public String getFileOldName() {
		return fileOldName;
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
	public void setLasteUpdateUserId(Long lasteUpdateUserId) {
		this.lasteUpdateUserId = lasteUpdateUserId;
	}
	/**
	 * 获取：
	 */
	public Long getLasteUpdateUserId() {
		return lasteUpdateUserId;
	}
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
}
