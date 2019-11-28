package io.renren.entity;

import java.io.Serializable;
import java.util.Date;

public class CahgViolationEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	//活动ID，主键
	private Integer id;
	//标题
	private String title;
	//内容
	private String content;
	//附件名称
	private String fileName;
	//附件原名
	private String fileOldName;
	//创建人ID
	private String createUserId;
	//创建人姓名
	private String userName;
	//创建时间
	private Date createDate;
	//最后修改人Id
	private String lastUpdateUserId;
	//最后修改时间
	private Date lastUpdateDate;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
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
	public String getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}
	
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getLastUpdateUserId() {
		return lastUpdateUserId;
	}
	public void setLastUpdateUserId(String lastUpdateUserId) {
		this.lastUpdateUserId = lastUpdateUserId;
	}
	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}
	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
}
