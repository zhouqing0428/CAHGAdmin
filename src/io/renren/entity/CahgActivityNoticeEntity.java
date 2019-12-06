package io.renren.entity;

import java.io.Serializable;
import java.util.Date;

public class CahgActivityNoticeEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	//活动ID，主键
	private Integer id;
	//活动标题
	private String title;
	//活动目标及方案
	private String content;
	//附件名称
	private String fileName;
	//附件原名
	private String fileOldName;
	//活动地点
	private String address;
	//活动时间
	private String time;
	//参加人员
	private String person;
	//备注
	private String remark;
	//创建人ID
	private String createUserId;
	//发布人
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getPerson() {
		return person;
	}
	public void setPerson(String person) {
		this.person = person;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
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
