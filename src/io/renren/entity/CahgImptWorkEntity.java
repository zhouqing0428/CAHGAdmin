package io.renren.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author 
 * @email 
 * @date 2017-07-19 11:17:50
 */
public class CahgImptWorkEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer imptWorkId;
	//标题
	private String title;
	//内容
	private String content;
	//
	private String author;
	//
	private String fileName;
	//
	private String fileOldName;
	//
	private Long createUserId;
	//
	private Date createDate;
	//
	private Long lastUpdateUserId;
	//
	private Date lastUpdateUserDate;
	//状态 ：0 显示，1 隐藏
	private Integer status;
	//
	private Integer deptId;
	//部门名称
	private String deptName;

	/**
	 * 设置：
	 */
	public void setImptWorkId(Integer imptWorkId) {
		this.imptWorkId = imptWorkId;
	}
	/**
	 * 获取：
	 */
	public Integer getImptWorkId() {
		return imptWorkId;
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
	 * 设置：内容
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * 获取：内容
	 */
	public String getContent() {
		return content;
	}
	/**
	 * 设置：
	 */
	public void setAuthor(String author) {
		this.author = author;
	}
	/**
	 * 获取：
	 */
	public String getAuthor() {
		return author;
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
	public void setLastUpdateUserDate(Date lastUpdateUserDate) {
		this.lastUpdateUserDate = lastUpdateUserDate;
	}
	/**
	 * 获取：
	 */
	public Date getLastUpdateUserDate() {
		return lastUpdateUserDate;
	}
	/**
	 * 设置：状态 ：0 显示，1 隐藏
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：状态 ：0 显示，1 隐藏
	 */
	public Integer getStatus() {
		return status;
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
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
}
