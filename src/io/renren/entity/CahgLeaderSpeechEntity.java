package io.renren.entity;

import java.io.Serializable;
import java.util.Date;


/**
 * 
 * 
 * @author 
 * @email 
 * @date 2017-06-29 14:58:49
 */
public class CahgLeaderSpeechEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键id
	private Integer leaderSpeechId;
	//标题
	private String title;
	//内容
	private String content;
	//附件名称
	private String fileName;
	//附件原名
	private String fileOldName;
	//备注
	private String remark;
	//创建人
	private Long createUserId;
	//创建时间
	private Date createDate;
	//最后修改人
	private Long lastUpdateUserId;
	//
	private Date lastUpdateDate;
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
	//状态: 0 显示 1 隐藏
	private String status;
	//领导讲话类型
	private String type;

	/**
	 * 设置：主键id
	 */
	public void setLeaderSpeechId(Integer leaderSpeechId) {
		this.leaderSpeechId = leaderSpeechId;
	}
	/**
	 * 获取：主键id
	 */
	public Integer getLeaderSpeechId() {
		return leaderSpeechId;
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
	 * 设置：备注
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * 获取：备注
	 */
	public String getRemark() {
		return remark;
	}
	/**
	 * 设置：创建人
	 */
	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
	}
	/**
	 * 获取：创建人
	 */
	public Long getCreateUserId() {
		return createUserId;
	}
	/**
	 * 设置：创建时间
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	/**
	 * 获取：创建时间
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
	 * 设置：状态: 0 显示 1 隐藏
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * 获取：状态: 0 显示 1 隐藏
	 */
	public String getStatus() {
		return status;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getFileOldName() {
		return fileOldName;
	}
	public void setFileOldName(String fileOldName) {
		this.fileOldName = fileOldName;
	}
}
