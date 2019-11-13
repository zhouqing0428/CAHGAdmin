package io.renren.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author 
 * @email 
 * @date 2017-08-15 11:45:48
 */
public class CahgWorkTableEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer workTableId;
	//标题
	private String title;
	//附件名称
	private String fileName;
	//附件原名
	private String fileOldName;
	//状态：0 显示，1 隐藏
	private Integer status;
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
	//
	private Date createTime;
	//工作表格类别id
	private Integer workTableCategoryId;
	//类别名称
	private String categoryName;
	//排序号
	private Integer rank;
	//添加人
	private Long createUserId;
	//添加时间
	private Date createDate;
	//最后修改人
	private Long lastUpdateUserId;
	//最后修改时间
	private Date lastUpdateDate;
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
	 * 设置：
	 */
	public void setWorkTableId(Integer workTableId) {
		this.workTableId = workTableId;
	}
	/**
	 * 获取：
	 */
	public Integer getWorkTableId() {
		return workTableId;
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
	 * 设置：附件名称
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	/**
	 * 获取：附件名称
	 */
	public String getFileName() {
		return fileName;
	}
	/**
	 * 设置：附件原名
	 */
	public void setFileOldName(String fileOldName) {
		this.fileOldName = fileOldName;
	}
	/**
	 * 获取：附件原名
	 */
	public String getFileOldName() {
		return fileOldName;
	}
	/**
	 * 设置：状态：0 显示，1 隐藏
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：状态：0 显示，1 隐藏
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * 设置：
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * 设置：工作表格类别id
	 */
	public void setWorkTableCategoryId(Integer workTableCategoryId) {
		this.workTableCategoryId = workTableCategoryId;
	}
	/**
	 * 获取：工作表格类别id
	 */
	public Integer getWorkTableCategoryId() {
		return workTableCategoryId;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	public Integer getRank() {
		return rank;
	}
	public void setRank(Integer rank) {
		this.rank = rank;
	}
}
