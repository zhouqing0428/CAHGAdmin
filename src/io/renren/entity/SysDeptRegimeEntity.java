package io.renren.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author 
 * @email 
 * @date 2017-12-13 17:49:11
 */
public class SysDeptRegimeEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer regimeId;
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
	private Long lastUpdateUserId;
	//
	private Integer deptId;

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
	public void setRegimeId(Integer regimeId) {
		this.regimeId = regimeId;
	}
	/**
	 * 获取：
	 */
	public Integer getRegimeId() {
		return regimeId;
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
