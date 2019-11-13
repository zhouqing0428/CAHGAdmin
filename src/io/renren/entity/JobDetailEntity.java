package io.renren.entity;

import java.util.Date;

public class JobDetailEntity {
	private int jobDetailId;
	private String content;     //内容
	private String fileName;   //文件名重名后
	private String fileOldName; //文件原名
	private Long createUserId;
	private Date createUserDate;
	private int jobId;
	private int flowUserId;
	private String flowUserName;  //跟进人
	private String nextUserName;   //流转至
	private int status;   //
	
	
	public String getFlowUserName() {
		return flowUserName;
	}
	public void setFlowUserName(String flowUserName) {
		this.flowUserName = flowUserName;
	}
	public String getNextUserName() {
		return nextUserName;
	}
	public void setNextUserName(String nextUserName) {
		this.nextUserName = nextUserName;
	}
	public int getJobDetailId() {
		return jobDetailId;
	}
	public void setJobDetailId(int jobDetailId) {
		this.jobDetailId = jobDetailId;
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
	public Long getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
	}
	public Date getCreateUserDate() {
		return createUserDate;
	}
	public void setCreateUserDate(Date createUserDate) {
		this.createUserDate = createUserDate;
	}
	public int getJobId() {
		return jobId;
	}
	public void setJobId(int jobId) {
		this.jobId = jobId;
	}
	public int getFlowUserId() {
		return flowUserId;
	}
	public void setFlowUserId(int flowUserId) {
		this.flowUserId = flowUserId;
	}
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	
}
