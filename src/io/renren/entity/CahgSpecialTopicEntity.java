package io.renren.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author 
 * @email 
 * @date 2017-07-20 10:13:31
 */
public class CahgSpecialTopicEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer specialTopicId;
	//标题
	private String title;
	//内容
	private String content;
	//作者
	private String author;
	//附件名称
	private String fileName;
	//附件原名
	private String fileOldName;
	//
	private Long createUserId;
	//
	private Date createDate;
	//
	private Long lastUpdateUserId;
	//
	private Date lastUpdateUserDate;
	//状态：0 显示，1 隐藏
	private Integer status;
	//部门id
	private Integer deptId;
	//主题类别id
	private Integer specialTopicCategoryId;
	//类别名称
	private String categoryName;
	//部门名称
    private String deptName;
    
    
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
	public void setSpecialTopicId(Integer specialTopicId) {
		this.specialTopicId = specialTopicId;
	}
	/**
	 * 获取：
	 */
	public Integer getSpecialTopicId() {
		return specialTopicId;
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
	 * 设置：作者
	 */
	public void setAuthor(String author) {
		this.author = author;
	}
	/**
	 * 获取：作者
	 */
	public String getAuthor() {
		return author;
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
	 * 设置：部门id
	 */
	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}
	/**
	 * 获取：部门id
	 */
	public Integer getDeptId() {
		return deptId;
	}
	/**
	 * 设置：主题类别id
	 */
	public void setSpecialTopicCategoryId(Integer specialTopicCategoryId) {
		this.specialTopicCategoryId = specialTopicCategoryId;
	}
	/**
	 * 获取：主题类别id
	 */
	public Integer getSpecialTopicCategoryId() {
		return specialTopicCategoryId;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
}
